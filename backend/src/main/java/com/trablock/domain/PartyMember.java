package com.trablock.domain;

import java.math.BigDecimal;

public class PartyMember {
    private long userId;            // 유저아이디
    private long partyId;           // 모임아이디
    private BigDecimal payment;     // 납입금액
    private boolean chief;          // 모임리더인지 여부(true이면 리더)
    private boolean warning;        // 미납경고(true이면 경고)

    private String email;			//유저 이메일
    private String name;			//유저 이름
    
    public PartyMember() {
    }

    public PartyMember(long userId, long partyId, BigDecimal payment, boolean chief, boolean warning) {
        this.userId = userId;
        this.partyId = partyId;
        this.payment = payment;
        this.chief = chief;
        this.warning = warning;
    }

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

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }
    
    

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PartyMember [userId=" + userId + ", partyId=" + partyId + ", payment=" + payment + ", chief=" + chief
				+ ", warning=" + warning + ", email=" + email + ", name=" + name + "]";
	}

	
}
