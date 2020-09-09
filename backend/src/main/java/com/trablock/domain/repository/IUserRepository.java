package com.trablock.domain.repository;

import java.util.List;

import org.mapstruct.Mapper;

import com.trablock.domain.EmailConfirm;
import com.trablock.domain.User;

@Mapper
public interface IUserRepository {
    List<User> list();
    User get(long id);
    User get(String email);

    long create(User user);
    int update(User user);
    int delete(long id);
    
	int isDupEmail(String email);
	int isConfirmedEmail(String email);
	int insertEmailConfirm(String email, String code);
	int checkConfirmCode(EmailConfirm emailConfirm);
	void deleteConfirmCode(String email);
}
