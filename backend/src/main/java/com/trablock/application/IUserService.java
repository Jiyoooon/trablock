package com.trablock.application;

import org.springframework.transaction.annotation.Transactional;

import com.trablock.domain.EmailConfirm;
import com.trablock.domain.User;

import java.util.List;

public interface IUserService {
    List<User> list();
    User get(long id);
    User get(String email);

    @Transactional
    User add(User user);

    @Transactional
    User update(User user);

    @Transactional
    void delete(long id);
	boolean isDupEmail(String email);
	boolean isConfirmedEmail(String email);
	void sendConfirmMail(String email) throws Exception;
	boolean checkConfirmCode(EmailConfirm emailConfirm);
	void removeConfirmCode(String email);
}
