package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.domain.wrapper.CashContract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class CashContractService implements ICashContractService {
	private static final Logger log = LoggerFactory.getLogger(CashContractService.class);

	@Value("${eth.erc20.contract}")
	private String ERC20_TOKEN_CONTRACT;

	@Value("${eth.admin.address}")
	private String ADMIN_ADDRESS;

	@Value("${eth.admin.wallet.filename}")
	private String WALLET_RESOURCE;

	@Value("${eth.encrypted.password}")
	private String PASSWORD;

	private CashContract cashContract;
	private ContractGasProvider contractGasProvider = new DefaultGasProvider();
	private Credentials credentials;

	@Autowired
	private Web3j web3j;

	public CashContractService() {
		try {
			credentials = WalletUtils.loadCredentials("sp199191", "admin.wallet");
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
//			cashContract = CashContract.deploy(web3j, credentials, contractGasProvider).send();
		cashContract = CashContract.load("0xAC62e8bc3c1DB6366147e9EbBd8c6076de53F1f7", web3j, credentials, contractGasProvider);
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 3 토큰 잔액 조회
	 * 
	 * @param eoa
	 * @return
	 */
	@Override
	public int getBalance(String eoa) {
		int balance = 0;
		try {
			balance = cashContract.balanceOf(new Address(eoa)).send().intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	public void buy(int value) {
		
	}

	public void getTokenInfromation() {
		try {
			System.out.println(credentials.getAddress());
			System.out.println(cashContract.totalSupply());
			System.out.println(cashContract.name().get());
			System.out.println(cashContract.decimals().getValue());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
