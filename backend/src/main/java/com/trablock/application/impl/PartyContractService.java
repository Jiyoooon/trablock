package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.application.IPartyContractService;
import com.trablock.application.IWalletService;
import com.trablock.domain.Party;
import com.trablock.domain.PartyWallet;
import com.trablock.domain.repository.IPartyRepository;
import com.trablock.domain.repository.IPartyWalletRepository;
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
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
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

	private ContractGasProvider contractGasProvider = new DefaultGasProvider();

	@Autowired
	private Web3j web3j;

	@Autowired
	private IWalletService walletService;
	
	@Autowired
	private IPartyRepository partyRepository;
	
	@Autowired
	private IPartyWalletRepository partyWalletRepository;
	
	/**
	 *
	 * @param party		모임계좌에 대한 요소에 대한 정보들이 담긴 매개객체
	 */
	@Override
	public void setPartyContract(Party party, String privateKey) {

	}
	
	public void pay(long partyId, String privateKey, long value) {
		Credentials credentials = Credentials.create(privateKey);		// 사용자에게 입력받는 개인키
		try {
			CashContract cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			
			cashContract.pay(BigInteger.valueOf(partyId), BigInteger.valueOf(value)).send();
			
			walletUpdate(partyId, cashContract);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void withdraw(long partyId, String privateKey, long value) {
		Credentials credentials = Credentials.create(privateKey);
		try {
			CashContract cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			
			cashContract.withDraw(BigInteger.valueOf(partyId), BigInteger.valueOf(value)).send();
			
			walletUpdate(partyId, cashContract);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void walletUpdate(long partyId, CashContract cashContract) {
		PartyWallet wallet = partyWalletRepository.getPartyWalletByPartyId(partyId);
		
		BigDecimal balance = wallet.getBalance();
		try {
			balance = new BigDecimal(cashContract.balanceOf(wallet.getAddress()).send());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		wallet.setBalance(balance);
		partyWalletRepository.update(wallet);
	}
}
