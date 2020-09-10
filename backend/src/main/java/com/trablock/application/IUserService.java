package com.trablock.application;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.EmailConfirm;
import com.trablock.domain.User;

import java.util.List;

public interface IUserService {
    //전체 유저 리스트
	List<User> list();
	
    //id로유저 정보 가져옴
	@Transactional
    User getUserInfo(long id);//남의 정보
	@Transactional
	User getMyInfo(long id);//내 정보
    @Transactional
    User add(User user);//회원가입
    @Transactional
    User update(User user);//회원수정
    @Transactional
    void delete(long id);//회원탈퇴
    
	boolean isDupEmail(String email);
	boolean isDupNickname(String nickname);
	boolean isConfirmedEmail(String email);
	void sendConfirmMail(String email) throws Exception;
	boolean checkConfirmCode(EmailConfirm emailConfirm);
	void removeConfirmCode(String email);
	
	
	//비번 확인
	public boolean checkPassword(String uid, String password);
	public void sendTmpPasswordEmail(String password, String email);
}
