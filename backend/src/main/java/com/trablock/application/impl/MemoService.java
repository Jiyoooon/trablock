package com.trablock.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trablock.application.IMemoService;
import com.trablock.domain.Memo;
import com.trablock.domain.exception.BadRequestException;
import com.trablock.domain.repository.IMemoRepository;

@Service
public class MemoService implements IMemoService {

	@Autowired
	private IMemoRepository memoRepository;
	
	@Override
	public int registeMemo(Memo memo, long userId) throws BadRequestException{
		//userid가 모임에 속하는 애인지 확인

		if(memoRepository.isUserInParty(userId, memo.getPartyId()) == 0) {
			throw new BadRequestException("내 모임의 메모만 등록 가능");
		}
		
		return memoRepository.createMemo(memo);
	}

	@Override
	public int updateMemo(Memo memo, long userId) throws BadRequestException{
		//userid가 모임에 속하는 애인지 확인
		if(memoRepository.isUserInParty(userId, memo.getPartyId()) == 0) {
			throw new BadRequestException("내 모임의 메모만 수정 가능");
		}
		
		return memoRepository.updateMemo(memo);
	}

	@Override
	public int removeMemo(long memoId, long userId) throws BadRequestException{
		//userid가 모임에 속하는 애인지 확인
		Memo memo = memoRepository.selectMemoById(memoId);
		System.out.println(userId+", "+memo.getPartyId());
		if(memoRepository.isUserInParty(userId, memo.getPartyId()) == 0) {
			throw new BadRequestException("내 모임의 메모만 삭제 가능");
		}
		
		return memoRepository.deleteMemo(memoId);
	}

	@Override
	public Memo searchMemoById(long memoId, long userId) throws BadRequestException {
		Memo memo = memoRepository.selectMemoById(memoId);

		if(memoRepository.isUserInParty(userId, memo.getPartyId()) == 0)
			throw new BadRequestException("내 모임의 메모만 조회 가능");
		
		return memo;
	}

	@Override
	public Memo searchMemoByDate(Memo memo) throws BadRequestException {
		return memoRepository.selectMemoByDate(memo);
	}

	@Override
	public List<Memo> searchMemoByParty(long partyId, long userId) throws BadRequestException{
		if(memoRepository.isUserInParty(userId, partyId) == 0)
			throw new BadRequestException("내 모임의 메모만 조회 가능");
		
		return memoRepository.selectMemoByParty(partyId);
	}

}
