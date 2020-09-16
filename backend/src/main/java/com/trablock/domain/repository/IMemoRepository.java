package com.trablock.domain.repository;

import java.util.List;

import org.mapstruct.Mapper;

import com.trablock.domain.Memo;

@Mapper
public interface IMemoRepository {
	public int createMemo(Memo memo);
	public int updateMemo(Memo memo);
	public int deleteMemo(long memoId);
	public List<Memo> selectMemoByParty(long partyId);
	public Memo selectMemoById(long memoId);
	
	public int isUserInParty(long userId, long partyId);
}
