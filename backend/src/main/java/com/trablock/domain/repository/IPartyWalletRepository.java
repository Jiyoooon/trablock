package com.trablock.domain.repository;

import com.trablock.domain.PartyWallet;
import com.trablock.domain.Wallet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPartyWalletRepository {
	// C
	long create(PartyWallet partyWallet);

	// R
	PartyWallet getPartyWalletByPartyId(long partyId);
	PartyWallet getPartyWalletByAddress(String address);

	// U
	int update(PartyWallet partyWallet);

	// D
	int delete(long partyId);
}