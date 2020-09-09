package com.trablock.application;

import java.util.List;

import com.trablock.domain.Record;

public interface IPurchaseRecordContractService {
    List<Record> getHistory(final String escrowContractAddress);
}
