package com.trablock.domain.repository;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.EthInfo;

public interface IEthInfoRepository {
    EthInfo get(String ethUrl);

    @Transactional
    void put(String ethUrl, String blockNumber);
}
