package com.trablock.application;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.Purchase;

public interface IEscrowContractService {
    @Transactional
    Purchase checkDeposit(int pid);
}
