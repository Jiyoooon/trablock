package com.trablock.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.trablock.domain.Wallet;

public interface IWalletRepository {
	List<Wallet> list();
	Wallet getWalletByOwnerId(long ownerId);
	Wallet getWalletByWAddress(String wAddress);
	
	long create(Wallet wallet);
	int update(Wallet wallet);

	int delete(long id);
}
