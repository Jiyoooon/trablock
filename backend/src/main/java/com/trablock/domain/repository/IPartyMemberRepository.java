package com.trablock.domain.repository;

import com.trablock.domain.Party;
import com.trablock.domain.PartyMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPartyMemberRepository {

    // R
    List<PartyMember> searchAll();                          // 모든 목록 조사
    List<Long> getPartyIdListByUserId(long userId);         // userId를 가지고 그 유저가 들어간 모임들의 id들을 검색.
    List<Long> getUserIdListByPartyId(long partyId);        // partyId를 가지고 그 모임에 속한 유저들의 id들을 검색.

    // C
    long create(PartyMember partyMember);

    // U
    int update(PartyMember partyMember);

    // D
    int delete(PartyMember partyMember);
}
