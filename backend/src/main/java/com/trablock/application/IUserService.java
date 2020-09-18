package com.trablock.application;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.EmailConfirm;
import com.trablock.domain.PartyMember;
import com.trablock.domain.User;

public interface IUserService {
    //전체 유저 리스트
	List<User> list();
	
	//모임별 유저 리스트
	List<PartyMember> partyUserList(long partyId) throws Exception;
	
    //id로유저 정보 가져옴
	@Transactional
    User getUserInfo(long id) throws Exception;//남의 정보
	@Transactional
	User getMyInfo(long id) throws Exception;//내 정보
	@Transactional
	User getUserInfo(String email) throws Exception;
    @Transactional
    User add(User user) throws Exception;//회원가입
    @Transactional
    User update(User user) throws Exception;//회원수정
    @Transactional
    void delete(String uid) throws Exception;//회원탈퇴
    
	boolean isDupEmail(String email) throws Exception;
	boolean isDupNickname(String nickname) throws Exception;
	boolean isConfirmedEmail(String email) throws Exception;
	void sendConfirmMail(String email) throws Exception;
	boolean checkConfirmCode(EmailConfirm emailConfirm) throws Exception;
	
	
	//비번 확인
	public boolean checkPassword(String uid, String password) throws Exception;
	public void sendTmpPasswordEmail(String password, String email) throws Exception;

	List<User> findUserByEmailOrNickname(String query);

}
