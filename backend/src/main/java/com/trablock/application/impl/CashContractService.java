package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.domain.CommonUtil;
import com.trablock.domain.CryptoUtil;
import com.trablock.domain.exception.ApplicationException;
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
import org.web3j.exceptions.MessageDecodingException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
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
			this.credentials = WalletUtils.loadCredentials("sp199191", "keyStore1");
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
//    	CashContract.deploy(web3j, credentials, gasPrice, gasLimit, initialWeiValue, totalSupply, tokenName, decimalUnits, tokenSymbol)
    	this.cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
    	
    	String deployedContractAddress = cashContract.getContractAddress();
    	System.out.println("deployedContractAddress : " + deployedContractAddress);
	}

    /**
     * TODO Sub PJT Ⅱ 과제 3
     * 토큰 잔액 조회
     * @param eoa
     * @return
     */
    @Override
    public int getBalance(String eoa) {
        int balance = 0;
        try {
        	balance = cashContract.balanceOf(new Address(eoa)).getValue().intValueExact();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return balance;
    }
    
    public void getTokenInfromation() throws InterruptedException, ExecutionException, IOException {
    	String symbol = cashContract.symbol().get().getValue();
    	String name = cashContract.name().get().getValue();
    	BigInteger decimal = cashContract.decimals().getValue();

    	System.out.println("symbol: " + symbol);
    	System.out.println("name: " + name);	
    	System.out.println("decimal: " + decimal.intValueExact());
    }
}
