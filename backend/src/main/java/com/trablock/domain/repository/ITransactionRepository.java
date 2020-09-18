package com.trablock.domain.repository;

import java.util.List;

import com.trablock.domain.Transaction;

public interface ITransactionRepository {
    List<Transaction> list();
    Transaction get(String hash);
    List<Transaction> getByAddress(String address);
    long add(Transaction tx);
}
