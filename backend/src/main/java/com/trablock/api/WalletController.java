package com.trablock.api;

import com.trablock.application.IWalletService;
import com.trablock.domain.Wallet;
import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;

import com.trablock.application.IWalletService;
import com.trablock.domain.Wallet;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;
import com.trablock.infrastructure.repository.WalletRepository;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class WalletController {
	public static final Logger logger = LoggerFactory.getLogger(WalletController.class);

	private IWalletService walletService;

	@Autowired
	public WalletController(IWalletService walletService) {
		Assert.notNull(walletService, "walletService 개체가 반드시 필요!");
		this.walletService = walletService;
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * 지갑 등록
	 * @param wallet
	 */
	@ApiOperation(value = "Register wallet of user")
	@RequestMapping(value = "/wallets", method = RequestMethod.POST)
	public Wallet register(@Valid @RequestBody Wallet wallet) {

		Wallet newWallet = this.walletService.register(wallet);
		if(newWallet == null) {
			throw new ApplicationException("지갑 정보를 등록할 수 없습니다.");
		}
		return newWallet;
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * 지갑 조회 by address
	 * @param address 지갑 주소
	 */
	@ApiOperation(value = "Fetch wallet by address")
	@RequestMapping(value = "/wallets/{address}", method = RequestMethod.GET)
	public Wallet get(@PathVariable String address) {
		Wallet searchWallet = new WalletRepository().get(address);
		if (searchWallet == null) {
			throw new ApplicationException("지갑을 찾을 수 없습니다.");
		}
		return searchWallet;
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * 지갑 조회 by user's id
	 * @param uid 사용자 id
	 */
	@ApiOperation(value = "Fetch wallet of user")
	@RequestMapping(value = "/wallets/of/{uid}", method = RequestMethod.GET)
	public Wallet getByUser(@PathVariable long uid) {
		Wallet searchWallet = this.walletService.get(uid);
		if (searchWallet == null) {
			throw new ApplicationException("지갑을 찾을 수 없습니다.");
		}
		return searchWallet;
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * 이더 충전 요청
	 * @param address 지갑 주소
	 */
	@ApiOperation(value = "Request ether")
	@RequestMapping(value ="/wallets/{address}", method = RequestMethod.PUT)
	public Wallet requestEth(@PathVariable String address){ // 테스트 가능하도록 일정 개수의 코인을 충전해준다.
		return this.walletService.requestEth(address);
	}
}
