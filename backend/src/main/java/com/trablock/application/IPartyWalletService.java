package com.trablock.application;

import com.trablock.domain.PartyWallet;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface IPartyWalletService
{
	PartyWallet get(long partyId);

	PartyWallet get(String walletAddress);

	@Transactional
	PartyWallet register(PartyWallet partyWallet);

	@Transactional
	PartyWallet syncBalance(String walletAddress, BigDecimal balance, int cash);

	@Transactional
	PartyWallet requestEth(String walletAddress);
}