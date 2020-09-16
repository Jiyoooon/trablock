package com.trablock.domain.repository;

import com.trablock.domain.Party;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IPartyRepository {

    // R
    List<Party> searchAll();
    Party searchById(long id);
    List<Party> searchByName(String name);

    // C
    long create(Party user);

    // U
    int update(Party user);

    // D
    int delete(long id);
}
