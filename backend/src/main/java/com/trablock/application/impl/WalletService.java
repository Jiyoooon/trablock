package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.application.IEthereumService;
import com.trablock.application.IWalletService;
import com.trablock.domain.Address;
import com.trablock.domain.Wallet;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.exception.NotFoundException;
import com.trablock.domain.repository.IWalletRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * TODO Sub PJT Ⅱ 과제 1, 과제 3
 * 과제 1: 지갑 관련 기능 구현
 * 1) 지갑 등록, 2) 지갑 조회, 3) 충전
 * 과제 3: 지갑 관련 기능 확장 구현
 * 1) 지갑 토큰 잔액 조회 추가
 *
 * IWalletService를 implements 하여 구현합니다.
 */
@Service
public class WalletService implements IWalletService
{
	private static final Logger log = LoggerFactory.getLogger(WalletService.class);

	private IWalletRepository walletRepository;
	private IEthereumService ethereumService;
	private ICashContractService cashContractService;

	@Autowired
	public WalletService(IWalletRepository walletRepository,
						 IEthereumService ethereumService,
						 ICashContractService cashContractService) {
		this.walletRepository = walletRepository;
		this.ethereumService = ethereumService;
		this.cashContractService = cashContractService;
	}

	/**
	 * 사용자 id로 지갑을 조회한다.
	 * @param userId
	 * @return
	 */
	@Override
	public Wallet get(final long userId) {
		Wallet wallet = walletRepository.get(userId);
		String walletAddress = wallet.getAddress();

		// 주소로 정보검색 요청
		BigInteger updatedBalance = ethereumService.getBalance(walletAddress);

		// 잔액정보가 불일치하면 업데이트
		if (updatedBalance != wallet.getBalance().toBigInteger()) {
			wallet.setBalance(BigDecimal.valueOf(Long.parseLong(updatedBalance.toString())));
		}

		return wallet;
	}

	/**
	 * 지갑을 DB에 등록한다.
	 * @param wallet
	 * @return
	 */
	@Override
	public Wallet register(final Wallet wallet) {
		long id = this.walletRepository.create(wallet);
		return this.walletRepository.get(id);
	}

	/**
	 * DB에 저장된 지갑주소의 정보와 이더리움의 잔액 정보를 동기화한다.
	 * @param walletAddress
	 * @return Wallet
	 */
	@Override
	public Wallet syncBalance(final String walletAddress, final BigDecimal balance, final int cash) {

		return null;
	}

	/**
	 * [지갑주소]로 이더를 송금하는 충전 기능을 구현한다.
	 * 무한정 충전을 요청할 수 없도록 조건을 두어도 좋다.
	 * @param walletAddress
	 * @return Wallet
	 */
	@Override
	public Wallet requestEth(String walletAddress) {
		// 1. 일단 지갑주소를 가지고 지갑객체를 얻는다.
		Wallet wallet = walletRepository.get(walletAddress);

		// 2. 지갑을 더 충전할 수 있는지 확인한다.
		if (!wallet.canRequestEth()) {
			return wallet;	// 2-1. 더 이상 충전할 수 없다면 원래 지갑 정보를 반환
		}

		// 2-2. 지갑에 5eth를 더 충전하도록 한다.
		ethereumService.requestEth(walletAddress);

		return wallet;
	}
}
