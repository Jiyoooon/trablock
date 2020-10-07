package com.trablock.application.impl;

import com.trablock.application.IEthereumService;
import com.trablock.domain.Address;
import com.trablock.domain.CommonUtil;
import com.trablock.domain.CryptoUtil;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.repository.ITransactionRepository;
import com.trablock.domain.wrapper.EthereumTransaction;

import com.trablock.application.IEthereumService;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.repository.ITransactionRepository;
import com.trablock.domain.wrapper.EthereumTransaction;
import com.trablock.domain.Address;
import com.trablock.domain.CommonUtil;
import com.trablock.domain.CryptoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.exceptions.MessageDecodingException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class EthereumService implements IEthereumService {

	private static final Logger log = LoggerFactory.getLogger(EthereumService.class);

	public static final BigInteger GAS_PRICE = BigInteger.valueOf(1L);
	public static final BigInteger GAS_LIMIT = BigInteger.valueOf(21_000L);

	// 사용할 이더리움 지갑의 주소
	@Value("${eth.admin.address}")
	private String ADMIN_ADDRESS;
	// 지갑의 암호화된 패스워드
	@Value("${eth.encrypted.password}")
	private String PASSWORD;
	// 사용할 이더리움 지갑의 키스토어
	@Value("${eth.admin.wallet.filename}")
	private String ADMIN_WALLET_FILE;

	private ITransactionRepository transactionRepository;

	@Autowired
	private Web3j web3j;

	@Autowired
	public EthereumService(ITransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * 이더리움으로부터 해당 주소의 잔액을 조회한다.
	 * @param address
	 * @return BigInteger
	 */
	@Override
	public BigInteger getBalance(String address){
		BigInteger bigInteger = null;
		try {
			bigInteger = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get().getBalance();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (MessageDecodingException e) {
			//"주소 길이/형식 오류"
			e.printStackTrace();
		}
		return bigInteger;
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * [주소]로 시스템에서 정한 양 만큼 이더를 송금한다.
	 * 이더를 송금하는 트랜잭션을 생성, 전송한 후 결과인
	 * String형의 트랜잭션 hash 값을 반환한다.
	 * @param address
	 * @return String 생성된 트랜잭션의 hash 반환 (참고, TransactionReceipt)
	 */
	@Override
	public String requestEth(final String address) // 특정 주소로 테스트 특정 양(5Eth) 만큼 충전해준다.
	{
		String hash = "";
		try {
			String privatetKey = "";
			// 1-1. keystore-비밀번호를 통해 복호화 -> PrivateKey(개인키)를 구함 ; 자격증명
//			Credentials credentials =  WalletUtils.loadCredentials("sp199191", "keyStore1");
//			Credentials credentials =  WalletUtils.loadCredentials("ssafy", "./src/main/resources/wallet/admin.json");

			// 1-2. 또는 keystore파일이 없을경우 사용자가 직접 privatekey를 입력함
			String privateKey = "0xc4ccf684bd7446f931ecbdeb55e49c1f63f591ccd0d4a16963df482510d75fd2";
			Credentials credentials = Credentials.create(privateKey);
			
			/* 2. 송금 트랜잭션 생성
			 */
			TransactionReceipt transactionReceipt = Transfer.sendFunds(
					web3j, 
					credentials, //from : credentials 보낼 증명서
					address, // toAddress : addr 받을 주소
			        BigDecimal.valueOf(5.0), //value : 보낼 값
			        Convert.Unit.ETHER) //unit : 단위
			        .send();
			
			// hash 결과 출력
			hash = transactionReceipt.getTransactionHash();
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
	}

	/**
	 * TODO Sub PJT Ⅲ 추가과제
	 * 이더리움으로부터 해당 주소의 잔액을 조회하고
	 * 동기화한 트랜잭션 테이블로부터 Address 정보의 trans 필드를 완성하여
	 * 정보를 반환한다.
	 * @param addr
	 * @return Address
	 */
	@Override
	public Address getAddress(String addr){
	
		return null;
	}

}
