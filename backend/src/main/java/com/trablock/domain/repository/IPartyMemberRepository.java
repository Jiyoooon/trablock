package com.trablock.domain.repository;

import com.trablock.domain.Party;
import com.trablock.domain.PartyMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPartyMemberRepository {

    // R
    List<PartyMember> searchAll();                          // 모든 목록 조사
    List<Long> getPartyIdListByUserId(long userId);         // userId를 가지고 그 유저가 들어간 모임들의 id들을 검색.
    List<Long> getUserIdListByPartyId(long partyId);        // partyId를 가지고 그 모임에 속한 유저들의 id들을 검색.
    List<PartyMember> getMemberListByPartyId(long partyId); // partyId를 가지고 그 모임에 속한 유저객체 리스트를 검색.
    PartyMember searchMemberByUserId(@Param("userId") long userId,@Param("partyId")  long partyId);        	// userId의 파티멤버 정보를 검색
    
    // C
    long create(PartyMember partyMember);

    // U
    int update(PartyMember partyMember);

    // D
    int delete(PartyMember partyMember);
}
