package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.application.IWalletService;
import com.trablock.domain.wrapper.CashContract;
import com.trablock.domain.wrapper.PartyContract;

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
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class CashContractService implements ICashContractService {
	private static final Logger log = LoggerFactory.getLogger(CashContractService.class);

//	@Value("${eth.erc20.contract}")
	private String ERC20_TOKEN_CONTRACT = "0x8fE03aE40a3F060bA75c9E9EfeA5C7863032E1D4";

	@Value("${eth.admin.address}")
	private String ADMIN_ADDRESS;

	@Value("${eth.admin.wallet.filename}")
	private String WALLET_RESOURCE;

	@Value("${eth.encrypted.password}")
	private String PASSWORD;

	private CashContract cashContract;
	private ContractGasProvider contractGasProvider = new DefaultGasProvider();
	private Credentials credentials;
	
	private PartyContract partyContract;

	@Autowired
	private Web3j web3j;

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
			credentials = WalletUtils.loadCredentials("sp199191", "admin.wallet");
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			balance = cashContract.balanceOf(eoa).send().intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	public String getName() {
		String name = null;
		try {
			credentials = WalletUtils.loadCredentials("sp199191", "admin.wallet");
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			name = cashContract.name().send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public String getSymbol() {
		String symbol = null;
		try {
			credentials = WalletUtils.loadCredentials("sp199191", "admin.wallet");
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			symbol = cashContract.symbol().send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symbol;
	}
	
	public void buy(int value, String privateKey) {
		try {
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j,  Credentials.create(privateKey), contractGasProvider);
			cashContract.buy(BigInteger.valueOf(value)).sendAsync().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
