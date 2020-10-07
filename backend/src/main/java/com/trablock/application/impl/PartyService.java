package com.trablock.application.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trablock.application.IPartyContractService;
import com.trablock.application.IPartyMemberService;
import com.trablock.application.IPartyService;
import com.trablock.application.IPartyWalletService;
import com.trablock.domain.Party;
import com.trablock.domain.PartyMember;
import com.trablock.domain.Withdraw;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.exception.NotFoundException;
import com.trablock.domain.repository.IPartyMemberRepository;
import com.trablock.domain.repository.IPartyRepository;

@Service
public class PartyService implements IPartyService {

    @Autowired
    private IPartyRepository partyRepository;
    @Autowired
    private IPartyMemberRepository partyMemberRepository;
    @Autowired
    private IPartyMemberService partyMemberService;
    @Autowired
    private IPartyWalletService partyWalletService;
    @Autowired
    private IPartyContractService partyContractService;

    @Override
    public List<Party> list() {
        // 모든 모임정보를 가져옴
        return this.partyRepository.searchAll();
    }

    @Override
    public Party get(long id) {//partyId
    	// id를 통해 특정 모임을 가져옴
        Party party = this.partyRepository.searchById(id);
        if (party == null) {
            throw new NotFoundException("모임 정보를 찾을 수 없습니다.");
        }
        
        party.setMemberlist(partyMemberRepository.getMemberListByPartyId(id));
        return party;
    }

    @Override
    public List<Party> get(String name) {
    	// name이 들어간 특정 모임들을 가져옴
        List<Party> partyList = this.partyRepository.searchByName(name);
        if (partyList == null || partyList.size() == 0) {
            throw new NotFoundException("모임 정보를 찾을 수 없습니다.");
        }
        return partyList;
    }

    @Override
    public Party add(Party party, List<Long> partyMemberIdList) {
        // 1. Party 추가
        this.partyRepository.create(party);

        // 2. PartyWallet 추가
        //  1) PartyWallet 객체 생성 및 partyId 초기화
        //PartyWallet partyWallet = new PartyWallet();
        //partyWallet.setPartyId(party.getId());
        //  2) 모임지갑 생성(JSON 파일 생성) 및 table insert
        //this.partyWalletService.register(partyWallet);

        // 3. PartyMember 추가
        //  모임인원 추가하는 부분 구현
        //  1) PartyMember에 데이터 추가
        //      [0] : chief를 true
        //      나머지 : chief를 false
        //      공통 : party_id, payment : 0, warning : false로 설정, user_id는 리스트대응값으로.
        long partyId = party.getId();
        boolean chief = true;
        for (long userId : partyMemberIdList) {
            this.partyMemberService.add(new PartyMember(userId, partyId, BigDecimal.valueOf(0), chief, false, false, 0));
            if (chief) {
                chief = false;
            }
        }

        return get(party.getId());
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

    
    //출금 신청
	@Override
	@Transactional
	public void registerWithdraw(Withdraw withdraw) {
		Party party = this.partyRepository.searchById(withdraw.getPartyId());
        if (party == null) {
            throw new NotFoundException("모임 정보를 찾을 수 없습니다.");
        }
		
        //파티 객체 가져와서 withdraw = true로 바꾸기
        party.setWithdraw(true);
        party.setWithdrawName(withdraw.getWithdrawName());
        party.setWithdrawAmount(withdraw.getWithdrawAmount());
        party.setPrivatekey(withdraw.getPrivatekey());
        partyRepository.update(party);
        
        System.out.println(party.toString());
        
        //userId는 isagree = 1로
        PartyMember user = partyMemberRepository.searchMemberByUserId(withdraw.getUserId(), withdraw.getPartyId());
        user.setIsagree(1);//동의
        partyMemberRepository.update(user);
	}
	
	
	

	@Override
	@Transactional
	public void agreeWithdraw(long userId, long partyId, int isagree) {
		//userId 멤버가 해당 파티 멤버인지 확인!
		List<PartyMember> members = partyMemberRepository.getMemberListByPartyId(partyId);
		//userId 멤버 동의 isagree = true
		int check = 0;// 확인 한 사람 수
		int agree = 0;// 동의 한 사람 수
		boolean isMyParty = false;
		
		for(PartyMember pm : members) {
			if(pm.getUserId() == userId) {
				pm.setIsagree(isagree);
				partyMemberRepository.update(pm);
				isMyParty = true;
			}
			if(pm.isIsagree() > 0) {
				if(pm.isIsagree() == 1) agree++;
				check++;
			}
		}
		
		if(!isMyParty) throw new NotFoundException("내 모임의 계좌만 접근 가능합니다.");
		
		Party party = partyRepository.searchById(partyId);
		
		if(check == members.size()) {
			//party에 해당하는 모두가 agree했을 경우 출금
			if(agree == members.size()) {
				//출금 스마트컨트랙트 함수 호출***********
				partyContractService.withdraw(partyId, party.getPrivatekey(), party.getWithdrawAmount());
				//withdrawName, withdrawAmount 남김
			}else {//거절 => 출금 실패
				//withdrawName, withdrawAmount 지움
				party.setWithdrawName(null);
				party.setWithdrawAmount(null);
			}

			party.setWithdraw(false);//출금정보 리셋
			party.setPrivatekey(null);//기본키 지움
			partyRepository.update(party);//출금 완료 후 파티 정보 수정
		}
		
	}

	
}
