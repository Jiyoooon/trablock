package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.application.IEthereumService;
import com.trablock.application.IPartyWalletService;
import com.trablock.domain.PartyWallet;
import com.trablock.domain.Wallet;
import com.trablock.domain.repository.IPartyWalletRepository;
import com.trablock.domain.repository.IWalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class PartyWalletService implements IPartyWalletService
{
	private static final Logger log = LoggerFactory.getLogger(PartyWalletService.class);

	@Autowired
	private IPartyWalletRepository partyWalletRepository;

	private IEthereumService ethereumService;
	private ICashContractService cashContractService;

	@Autowired
	public PartyWalletService(IEthereumService ethereumService, ICashContractService cashContractService) {
		this.ethereumService = ethereumService;
		this.cashContractService = cashContractService;
	}

	/**
	 * 사용자 id로 지갑을 조회한다.
	 * @param partyId
	 * @return
	 */
	@Override
	public PartyWallet get(long partyId) {
		PartyWallet wallet = this.partyWalletRepository.getPartyWalletByPartyId(partyId);
		String walletAddress = wallet.getAddress();

		// 주소로 정보검색 요청
		BigInteger updatedBalance = this.ethereumService.getBalance(walletAddress);

		// 잔액정보가 불일치하면 업데이트
		if (updatedBalance != wallet.getBalance().toBigInteger()) {
			wallet.setBalance(BigDecimal.valueOf(Long.parseLong(updatedBalance.toString())));
		}

		return wallet;
	}


	@Override
	public PartyWallet get(String address) {
		PartyWallet wallet = this.partyWalletRepository.getPartyWalletByAddress(address);

		// 주소로 정보검색 요청
		BigInteger updatedBalance = this.ethereumService.getBalance(address);

		// 잔액정보가 불일치하면 업데이트
		if (updatedBalance != wallet.getBalance().toBigInteger()) {
			wallet.setBalance(BigDecimal.valueOf(Long.parseLong(updatedBalance.toString())));
		}

		return wallet;
	}

	/**
	 * 모임지갑을 DB에 등록한다.
	 * @param partyWallet
	 * @return
	 */
	@Override
	public PartyWallet register(final PartyWallet partyWallet) {

		String walletPassword = "1234";
		String walletDirectory = "./src/main/resources/partywallet";

		String walletName = null;
		try {
			walletName = WalletUtils.generateNewWalletFile(walletPassword, new File(walletDirectory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("wallet location: " + walletDirectory + "/" + walletName);


		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(walletPassword, walletDirectory + "/" + walletName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String accountAddress = credentials.getAddress();
		System.out.println("Account address: " + credentials.getAddress());

		partyWallet.setAddress(credentials.getAddress());
		this.partyWalletRepository.create(partyWallet);

		return get(partyWallet.getAddress());
	}

	/**
	 * DB에 저장된 지갑주소의 정보와 이더리움의 잔액 정보를 동기화한다.
	 * @param walletAddress
	 * @return Wallet
	 */
	@Override
	public PartyWallet syncBalance(final String walletAddress, final BigDecimal balance, final int cash) {

		return null;
	}

	/**
	 * [지갑주소]로 이더를 송금하는 충전 기능을 구현한다.
	 * 무한정 충전을 요청할 수 없도록 조건을 두어도 좋다.
	 * @param walletAddress
	 * @return Wallet
	 */
	@Override
	public PartyWallet requestEth(String walletAddress) {
		// 1. 일단 지갑주소를 가지고 지갑객체를 얻는다.
		PartyWallet wallet = partyWalletRepository.getPartyWalletByAddress(walletAddress);

		// 2-2. 지갑에 5eth를 더 충전하도록 한다.
		ethereumService.requestEth(walletAddress);

		return wallet;
	}
}