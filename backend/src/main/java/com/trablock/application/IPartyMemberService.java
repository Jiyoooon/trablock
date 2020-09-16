package com.trablock.application;

import com.trablock.domain.Party;
import com.trablock.domain.PartyMember;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPartyMemberService {
    List<PartyMember> list();
    List<Long> getPartyIdListByUserId(long userId);

    @Transactional
    long add(PartyMember partyMember);

    @Transactional
    long update(PartyMember partyMember);

    @Transactional
    void delete(PartyMember partyMember);
}
