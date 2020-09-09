package com.trablock.application;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.Purchase;
import com.trablock.domain.PurchaseInfo;

import java.util.List;

public interface IPurchaseService {
    List<Purchase> list();
    Purchase get(long id);
    Purchase getByPurchaseId(int pid);
    List<PurchaseInfo> getBySeller(int id);
    List<PurchaseInfo> getByBuyer(int id);

    @Transactional
    Purchase create(Purchase purchase);

    @Transactional
    Purchase updateState(int pid, String state);
}
