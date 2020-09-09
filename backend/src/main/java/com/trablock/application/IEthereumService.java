package com.trablock.application;

import java.math.BigInteger;

import com.trablock.domain.Address;

public interface IEthereumService {
    String requestEth(String address);
    BigInteger getBalance(String address);

    Address getAddress(String address);
}
