package com.trablock.application;

import java.math.BigDecimal;

import com.trablock.domain.Party;

public interface IPartyContractService {
    public void setPartyContract(Party party, String privateKey);
    public void pay(long userId, long partyId, String privateKey, long value);
    public void withdraw(long partyId, String privateKey, BigDecimal value);
}
