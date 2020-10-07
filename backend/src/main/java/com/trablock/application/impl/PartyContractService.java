package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.application.IPartyContractService;
import com.trablock.application.IPartyWalletService;
import com.trablock.application.IWalletService;
import com.trablock.domain.Party;
import com.trablock.domain.PartyMember;
import com.trablock.domain.PartyWallet;
import com.trablock.domain.Wallet;
import com.trablock.domain.repository.IPartyMemberRepository;
import com.trablock.domain.repository.IPartyRepository;
import com.trablock.domain.repository.IPartyWalletRepository;
import com.trablock.domain.repository.IWalletRepository;
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
	private IPartyWalletService partyWalletService;
	
	@Autowired
	private IPartyRepository partyRepository;

	@Autowired
	private IWalletRepository walletRepository;
    @Autowired
    private IPartyMemberRepository partyMemberRepository;
	@Autowired
	private IPartyWalletRepository partyWalletRepository;

	@Autowired
	public PartyContractService(IWalletService walletService, IPartyWalletService partyWalletService) {
		Assert.notNull(walletService, "walletService 개체가 반드시 필요!");
		this.walletService = walletService;
		this.partyWalletService = partyWalletService;
	}

	/**
	 *
	 * @param party		모임계좌에 대한 요소에 대한 정보들이 담긴 매개객체
	 */
	@Override
	public void setPartyContract(Party party, String privateKey) {
		// 컨트랙트를 로드 하기 위해 컨트랙트 주소와 credentials에 쓰일 개인키를 입력받는다(개인키는 front를 통해 사용자에게 입력받는다.)
		// 모임계좌를 만드는 과정 역시 수수료가 드는 과정이므로 개인키가 필요하다.
		Credentials credentials = Credentials.create(privateKey);	// 유저의 개인키를 토대로 지갑 객체를 생성한다.

		// 1. 배포되어 있는 cash 컨트랙트를 로드 한다.
		CashContract cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
		try {
			// 2. cash 컨트랙트의 함수를 통해 모임계좌를 생성
			cashContract.createParties(new BigInteger(String.valueOf(party.getId())), new BigInteger(String.valueOf(party.getTarget())), new BigInteger(String.valueOf(100)), new BigInteger(String.valueOf(0))).send();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3. 모임계좌의 컨트랙트 주소를 db에 저장한다.(PartyWallet의 table에 insert)
		String partyWalletAddress = null;
		try {
			partyWalletAddress = cashContract.getParties(new BigInteger(String.valueOf(party.getId()))).send();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3-1. id(auto) / party_id(위에서 쓴거) / address(위에서 받은거) / balance(처음 생성하는 것이므로 0으로 초기화)
		PartyWallet partyWallet = new PartyWallet();
		partyWallet.setPartyId(party.getId());
		partyWallet.setAddress(partyWalletAddress);
		partyWallet.setBalance(new BigDecimal(0));
		partyWalletService.register(partyWallet);
	}
	
	public void pay(long userId, long partyId, String privateKey, long value) {
		Wallet wallet = walletRepository.getWalletByOwnerId(userId);
		if(wallet.getTBC().compareTo(new BigDecimal(value)) < 0) {
			walletService.changeTBC((int)value/1000 + 1, privateKey);
		}
		
		Credentials credentials = Credentials.create(privateKey);		// 사용자에게 입력받는 개인키
		try {
			CashContract cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			cashContract.pay(BigInteger.valueOf(partyId), BigInteger.valueOf(value)).send();
			PartyMember partyMember = partyMemberRepository.searchMemberByUserId(userId, partyId);
			partyMember.setIspay(true);
			partyMemberRepository.update(partyMember);			
			
			walletUpdate(partyId, cashContract);

			Party party = partyRepository.searchById(partyId);
			BigDecimal val = party.getTotalAmount();
			long newVal = val.longValue();
			newVal += value;
			party.setTotalAmount(new BigDecimal(newVal));
			partyRepository.update(party);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void withdraw(long partyId, String privateKey, BigDecimal value) {
		Credentials credentials = Credentials.create(privateKey);
		try {
			CashContract cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			
			cashContract.withDraw(BigInteger.valueOf(partyId), value.toBigInteger()).send();
			
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
