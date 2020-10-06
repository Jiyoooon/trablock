package com.trablock.domain;

import java.math.BigDecimal;

public class Withdraw {
	private long userId;					// 출금 신청한 유저 아이디
	private long partyId;					// 출금 할 모임 번호
	private String withdrawName;			// 누가 출금 신청했는지
    private BigDecimal withdrawAmount;		// 출금 양
    private String privatekey;
    
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPartyId() {
		return partyId;
	}
	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}
	public String getWithdrawName() {
		return withdrawName;
	}
	public void setWithdrawName(String withdrawName) {
		this.withdrawName = withdrawName;
	}
	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
	public String getPrivatekey() {
		return privatekey;
	}
	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}
	@Override
	public String toString() {
		return "Withdraw [userId=" + userId + ", partyId=" + partyId + ", withdrawName=" + withdrawName
				+ ", withdrawAmount=" + withdrawAmount + ", privatekey=" + privatekey + "]";
	}
    
}
