package com.trablock.domain.wrapper;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * TODO Sub PJT Ⅱ 과제 3 ERC-20 Token's Wrapper Class를 생성하여 코드를 대체한다.
 */

public class CashContract extends Contract {

	private static final String BINARY = "contract binary key";

	@Autowired
	private Web3j web3j;

	protected CashContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
		// TODO Auto-generated constructor stub
	}

	protected CashContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public TransactionReceipt approve(Address _spender, Uint256 _amount) {
		return null;
	}

	public TransactionReceipt transferFrom(Address _from, Address _to, Uint256 _amount){
		return null;
	}

	public Uint256 balanceOf(Address _owner) {
		return null;
	}

	public Future<Uint256> allowance(Address _owner, Address _spender) {
		return null;
	}
}
