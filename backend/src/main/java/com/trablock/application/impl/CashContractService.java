package com.trablock.application.impl;

import com.trablock.application.ICashContractService;
import com.trablock.domain.Party;
import com.trablock.domain.Wallet;
import com.trablock.domain.repository.IWalletRepository;
import com.trablock.domain.wrapper.CashContract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;

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

	@Autowired
	private IWalletRepository walletRepository;

	private CashContract cashContract;
	private ContractGasProvider contractGasProvider = new DefaultGasProvider();
	private Credentials credentials;

	private Web3j web3j;

	public CashContractService() {
		web3j = Web3j.build(new HttpService());
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 3 토큰 잔액 조회
	 * 
	 * @param eoa
	 * @return
	 */
	@Override
	public BigInteger getBalance(String eoa) {
		BigInteger balance = BigInteger.valueOf(0);
		try {
			credentials = WalletUtils.loadCredentials(PASSWORD, WALLET_RESOURCE);
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			balance = cashContract.balanceOf(eoa).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	public String getName() {
		String name = null;
		try {
			credentials = WalletUtils.loadCredentials(PASSWORD, WALLET_RESOURCE);
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
			credentials = WalletUtils.loadCredentials(PASSWORD, WALLET_RESOURCE);
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			symbol = cashContract.symbol().send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symbol;
	}

	public void buy(int value, String privateKey) {
		try {
			credentials = Credentials.create(privateKey);
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			cashContract.buy(BigInteger.valueOf(value)).sendAsync().get();
			updateWallet(credentials.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String createParty(Party party, String privateKey) {
		String partyAddress = "";
		try {
			credentials = Credentials.create(privateKey);
			cashContract = CashContract.load(ERC20_TOKEN_CONTRACT, web3j, credentials, contractGasProvider);
			partyAddress = cashContract
					.createParties(BigInteger.valueOf(party.getId()), party.getTarget().toBigInteger(),
							BigInteger.valueOf(party.getPayCycle() ? 1 : 0), party.getExitFee().toBigInteger())
					.send().getLogs().get(0).getTopics().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partyAddress;
	}

	public void updateWallet(String walletAddress) {
		BigInteger tbc = getBalance(walletAddress);
		Wallet wallet = walletRepository.getWalletByWAddress(walletAddress);
		wallet.setTBC(new BigDecimal(tbc));
		walletRepository.update(wallet);
	}
}
