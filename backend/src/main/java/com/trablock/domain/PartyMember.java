package com.trablock.domain;

import java.math.BigDecimal;

public class PartyMember {
    private long userId;            // 유저아이디
    private long partyId;           // 모임아이디
    private BigDecimal payment;     // 납입금액
    private boolean chief;          // 모임리더인지 여부(true이면 리더)
    private boolean warning;        // 미납경고(true이면 경고)

    public PartyMember() {
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

    @Override
    public String toString() {
        return "PartyMember{" +
                "userId=" + userId +
                ", partyId=" + partyId +
                ", payment=" + payment +
                ", chief=" + chief +
                ", warning=" + warning +
                '}';
    }
}
