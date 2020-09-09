package com.trablock.domain;

import java.math.BigDecimal;

public class PartyWallet {
    private long id;                // 모임 지갑 id
    private long partyId;           // 모임 id
    private String address;         // 모임 지갑주소
    private BigDecimal balance;     // 지갑 잔액

    public PartyWallet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
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

    @Override
    public String toString() {
        return "PartyWallet{" +
                "id=" + id +
                ", partyId=" + partyId +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}
