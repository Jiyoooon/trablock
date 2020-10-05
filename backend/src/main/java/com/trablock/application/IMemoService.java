package com.trablock.application;

import java.util.List;

import com.trablock.domain.Memo;
import com.trablock.domain.exception.BadRequestException;

public interface IMemoService {
	public int registeMemo(Memo memo, long userId) throws BadRequestException;
	public int updateMemo(Memo memo, long userId) throws BadRequestException;
	public int removeMemo(long memoId, long userId) throws BadRequestException;

	public Memo searchMemoById(long memoId, long userId) throws BadRequestException;
	public Memo searchMemoByDate(Memo memo) throws BadRequestException;
	
	public List<Memo> searchMemoByParty(long partyId, long userId) throws BadRequestException;
}
