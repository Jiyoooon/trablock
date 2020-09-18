package com.trablock.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Sub PJT Ⅱ
 */
@Data
public class Wallet {
	private long id;
	private long ownerId;
	private String address;
	private BigDecimal balance = BigDecimal.valueOf(0);
	private int receivingCount = 0;
	private int cash = 0;

	public Wallet(){}
	public Wallet(long ownerId,String address, BigDecimal balance){
		this.ownerId = ownerId;
		this.address = address;
		this.balance = balance;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getReceivingCount() {
		return receivingCount;
	}

	public void setReceivingCount(int receivingCount) {
		this.receivingCount = receivingCount;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public boolean canRequestEth(){
		return this.receivingCount < 10;
	}
}
