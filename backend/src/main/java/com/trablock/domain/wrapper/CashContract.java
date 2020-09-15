package com.trablock.domain.wrapper;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * TODO Sub PJT Ⅱ 과제 3
 * ERC-20 Token's Wrapper Class를 생성하여 코드를 대체한다.
 */

public class CashContract extends Contract{
	
	private static final String BINARY = "contract binary key";
	
	@Autowired
	private Web3j web3j;

	protected CashContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
		// TODO Auto-generated constructor stub
	}
	
	protected CashContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
