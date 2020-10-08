package com.trablock.domain;

import java.math.BigDecimal;

public class PartyMember {
    private long userId;            // 유저아이디
    private long partyId;           // 모임아이디
    private BigDecimal payment;     // 납입금액
    private boolean chief;          // 모임리더인지 여부(true이면 리더)
    private boolean warning;        // 미납경고(true이면 경고)
    private boolean ispay;			// 이번달 냈는지 여부
    private int isagree;			// 출금동의 여부(0 : 아직 확인 안함, 1 : 동의, 2 : 거절)

    private String email;			//유저 이메일
    private String name;			//유저 이름
    
    public PartyMember() {
    }

    public PartyMember(long userId, long partyId, BigDecimal payment, boolean chief, boolean warning, boolean ispay,
    		int isagree) {
		super();
		this.userId = userId;
		this.partyId = partyId;
		this.payment = payment;
		this.chief = chief;
		this.warning = warning;
		this.ispay = ispay;
		this.isagree = isagree;
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
	
	public boolean isIspay() {
		return ispay;
	}

	public void setIspay(boolean ispay) {
		this.ispay = ispay;
	}

	public int isIsagree() {
		return isagree;
	}

	public void setIsagree(int isagree) {
		this.isagree = isagree;
	}

	
	@Override
	public String toString() {
		return "PartyMember [userId=" + userId + ", partyId=" + partyId + ", payment=" + payment + ", chief=" + chief
				+ ", warning=" + warning + ", ispay=" + ispay + ", isagree=" + isagree + ", email=" + email + ", name="
				+ name + "]";
	}

	

	
}
