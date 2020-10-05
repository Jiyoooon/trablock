package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.application.IPartyContractService;
import com.trablock.application.IWalletService;
import com.trablock.domain.Party;
import com.trablock.domain.wrapper.CashContract;
import com.trablock.domain.wrapper.PartyContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class PartyContractService implements IPartyContractService {
	private static final Logger log = LoggerFactory.getLogger(PartyContractService.class);

	@Value("${eth.erc20.contract}")
	private String ERC20_TOKEN_CONTRACT;

	@Value("${eth.admin.address}")
	private String ADMIN_ADDRESS;

	@Value("${eth.admin.wallet.filename}")
	private String WALLET_RESOURCE;

	@Value("${eth.encrypted.password}")
	private String PASSWORD;

	private PartyContract partyContract;
	private ContractGasProvider contractGasProvider = new DefaultGasProvider();

	@Autowired
	private Web3j web3j;


	private IWalletService walletService;

	@Autowired
	public PartyContractService(IWalletService walletService) {
		Assert.notNull(walletService, "walletService 개체가 반드시 필요!");
		this.walletService = walletService;
	}

	/**
	 *
	 * @param party		모임계좌에 대한 요소에 대한 정보들이 담긴 매개객체
	 */
	@Override
	public void setPartyContract(Party party) {
		String address = walletService.get(party.getMembers().get(0)).getAddress();			// 주소
		BigInteger goalAmount = party.getTarget().toBigInteger();							// 목표금액
		BigInteger deadline = null;															// 모임통장 지속시간(일(日))
		try {
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(party.getStartDate());
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(party.getEndDate());
			long diff = end.getTime() - start.getTime();
			deadline = new BigInteger(String.valueOf(diff));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> memberList = new ArrayList<>();                                    	// 멤버들의 지갑주소 (address)
		for (Long id:party.getMembers()) {
			memberList.add(walletService.get(id).getAddress());
		}

		Credentials credentials = Credentials.create("bcf332aec0530df57aaa129c95702d0f4c5317b1e189a4e69812938328e799d1");		// 사용자에게 입력받는 개인키
		String contractAddress = null;
		try {
			PartyContract partyContract = PartyContract.deploy(web3j, credentials, contractGasProvider, BigInteger.ONE, address, goalAmount, deadline, BigInteger.ONE, memberList).send();
			contractAddress = partyContract.getContractAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 송금 트랜잭션 생성(개인계좌 -> 모임계좌)
		try {
			// Decrypt and open the wallet into a Credential object
			System.out.println("Account address: " + credentials.getAddress());
			System.out.println("Balance: " + Convert.fromWei(web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));

			// Get the latest nonce
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
			BigInteger nonce =  ethGetTransactionCount.getTransactionCount();

			// Recipient address(받는 사람 주소 = contract 주소)
			String recipientAddress = "0xc7DAc56F545Ed286E6dAb2660524D169A7D308E3";

			// Value to transfer (in wei)
			BigInteger value = Convert.toWei("1", Convert.Unit.ETHER).toBigInteger();

			// Gas Parameters
			BigInteger gasLimit = BigInteger.valueOf(21000);
			BigInteger gasPrice = Convert.toWei("1", Convert.Unit.GWEI).toBigInteger();

			// Prepare the rawTransaction
			RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
					nonce,
					gasPrice,
					gasLimit,
					recipientAddress,
					value);

			// Sign the transaction
			byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
			String hexValue = Numeric.toHexString(signedMessage);

			// Send transaction
			EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
			String transactionHash = ethSendTransaction.getTransactionHash();
			System.out.println("transactionHash: " + transactionHash);

			// Wait for transaction to be mined
			Optional<TransactionReceipt> transactionReceipt = null;
			do {
				System.out.println("checking if transaction " + transactionHash + " is mined....");
				EthGetTransactionReceipt ethGetTransactionReceiptResp = web3j.ethGetTransactionReceipt(transactionHash).send();
				transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
				Thread.sleep(3000); // Wait 3 sec
			} while(!transactionReceipt.isPresent());

			System.out.println("Transaction " + transactionHash + " was mined in block # " + transactionReceipt.get().getBlockNumber());
			System.out.println("Balance: " + Convert.fromWei(web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));


		} catch (IOException | InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
