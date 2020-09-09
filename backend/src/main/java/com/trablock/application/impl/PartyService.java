package com.trablock.application.impl;

import com.trablock.application.IPartyService;
import com.trablock.domain.Party;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.repository.IPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService implements IPartyService {

    @Autowired
    private IPartyRepository partyRepository;

    @Override
    public List<Party> list() {
        // 모든 모임정보를 가져옴
        return this.partyRepository.searchAll();
    }

    @Override
    public Party get(long id) {
    	// id를 통해 특정 모임을 가져옴
        return this.partyRepository.searchById(id);
    }

    @Override
    public List<Party> get(String name) {
    	// name이 들어간 특정 모임들을 가져옴
    	return this.partyRepository.searchByName(name);
    }

    @Override
    public Party add(Party party) {
        long id = this.partyRepository.create(party);
        return this.partyRepository.searchById(id);
    }

    @Override
    public Party update(Party party) {

        Party found = this.partyRepository.searchById(party.getId());
        if(found == null)
            throw new ApplicationException("모임 정보를 찾을 수 없습니다.");

        int affected = this.partyRepository.update(party);
        if(affected == 0)
            throw new ApplicationException("모임 정보 수정 처리가 반영되지 않았습니다.");

        return this.partyRepository.searchById(party.getId());
    }

    @Override
    public void delete(long id) {
        this.partyRepository.delete(id);
    }


}
