package com.trablock.application.impl;

import com.trablock.application.IPartyMemberService;
import com.trablock.application.IPartyService;
import com.trablock.domain.Party;
import com.trablock.domain.PartyMember;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.exception.NotFoundException;
import com.trablock.domain.repository.IPartyMemberRepository;
import com.trablock.domain.repository.IPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyMemberService implements IPartyMemberService {

    @Autowired
    private IPartyMemberRepository partyMemberRepository;

    @Override
    public List<PartyMember> list() {
        List<PartyMember> list = partyMemberRepository.searchAll();
        if (list == null || list.size() == 0) {
            throw new NotFoundException("partyMember 데이터가 존재하지 않습니다.");
        }
        return list;
    }

    @Override
    public List<Long> getPartyIdListByUserId(long userId) {
        List<Long> partyIdList = partyMemberRepository.getPartyIdListByUserId(userId);
        if (partyIdList == null || partyIdList.size() == 0)
            throw new NotFoundException("userId에 대응되는 partyId가 없습니다.");
        return partyIdList;
    }

    @Override
    public long add(PartyMember partyMember) {
        try {
            return partyMemberRepository.create(partyMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long update(PartyMember partyMember) {
        try {
            return partyMemberRepository.update(partyMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(PartyMember partyMember) {
        try {
            partyMemberRepository.delete(partyMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
