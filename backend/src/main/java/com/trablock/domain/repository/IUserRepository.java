package com.trablock.domain.repository;

import java.util.List;

import org.mapstruct.Mapper;

import com.trablock.domain.EmailConfirm;
import com.trablock.domain.Party;
import com.trablock.domain.User;
import com.trablock.domain.Wallet;

@Mapper
public interface IUserRepository {
    List<User> list();//전체 유저 리스트(유저 검색할때)
    
    
    User getUserById(long id);
    User getUserByEmail(String email);
    
    List<Wallet> getWallets(long userid, long mine);
    List<Party> getParties(long userid);

    long create(User user);
    int update(User user);
    int delete(long userid);

    
    //닉네임 중복
    int isDupNickname(String nickname);
    //이메일 인증
	int isDupEmail(String email);
	int isConfirmedEmail(String email);
	int insertEmailConfirm(String email, String code);
	int checkConfirmCode(EmailConfirm emailConfirm);
	void deleteConfirmCode(String email);
	
	List<User> userInGroup(long partyId);//그룹별 유저정보
	
	int checkPassword(String uid, String password);
	void updatePasswordByEmail(String password, String email);
	
	long selectNextUserId();
}
