package com.trablock.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.trablock.domain.Wallet;

public interface IWalletRepository {
	List<Wallet> list();
	Wallet get(long id);
	Wallet get(String wAddress);
	
	long create(Wallet wallet);
	int updateBalance(String wAddress, BigDecimal balance, int cash);
	int updateRequestNo(String wAddress);
}
