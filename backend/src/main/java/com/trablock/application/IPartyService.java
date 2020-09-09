package com.trablock.application;

import com.trablock.domain.Party;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPartyService {
    List<Party> list();
    Party get(long id);
    List<Party> get(String name);

    @Transactional
    Party add(Party party);

    @Transactional
    Party update(Party party);

    @Transactional
    void delete(long id);
}
