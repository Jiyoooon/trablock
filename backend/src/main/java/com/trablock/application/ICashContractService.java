package com.trablock.application;

import java.math.BigInteger;

public interface ICashContractService {
    public BigInteger getBalance(String eoa);
    public String getName();
    public String getSymbol();
    public void buy(int value, String privateKey);
    public void updateWallet(String walletAddress);
}
