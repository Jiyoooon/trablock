package com.trablock.application.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.trablock.application.IUserService;
import com.trablock.domain.EmailConfirm;
import com.trablock.domain.TempKey;
import com.trablock.domain.User;
import com.trablock.domain.exception.ApplicationException;
import com.trablock.domain.repository.IUserRepository;
import com.trablock.util.SHA256;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> list() {
        return this.userRepository.list();
    }

    //다른 유저 정보 가져옴
    @Override
    public User getUserInfo(long id) {
    	User user = this.userRepository.getUserById(id);
	    user.setWallets(this.userRepository.getWallets(id, 0));
    	
    	return user;
    }

    //로그인한 유저 정보 가져옴 => token
    @Override
    public User getMyInfo(long id) {
    	User user = this.userRepository.getUserById(id);
	    user.setWallets(this.userRepository.getWallets(id, 1));
	    user.setParties(this.userRepository.getParties(id));
	    
	    return user;
    }

    //회원가입
    @Override
    public User add(User user) {
        long id = this.userRepository.selectNextUserId();
        
        user.setPassword(SHA256.testSHA256(user.getPassword()));
        
        this.userRepository.create(user);
        return getMyInfo(id);
    }

    //회원 수정
    @Override
    public User update(User user) {

        User found = this.userRepository.getUserById(user.getId());
        if(found == null)
            throw new ApplicationException("회원 정보를 찾을 수 없습니다.");

        if(user.getId() == 0)
            user.setId(found.getId());
        if(user.getNickname() == null)
            user.setNickname(found.getNickname());
        
        if(user.getPassword() == null)
            user.setPassword(found.getPassword());
        else user.setPassword(SHA256.testSHA256(user.getPassword()));

        int affected = this.userRepository.update(user);
        if(affected == 0)
            throw new ApplicationException("회원수정 처리가 반영되지 않았습니다.");

        return this.userRepository.getUserById(user.getId());
    }

    @Override
    public void delete(long id) {
        this.userRepository.delete(id);
    }


    //이메일 중복확인
	@Override
	public boolean isDupEmail(String email) {
		int r = userRepository.isDupEmail(email);
		if(r == 0) throw new ApplicationException("이미 존재하는 계정");
		return r > 0 ? true : false;
	}


	//
	@Override
	public boolean isConfirmedEmail(String email) {
		return userRepository.isConfirmedEmail(email) > 0 ? true : false;
	}


	//회원가입 본인 인증키 메일 보내기
	@Override
	public void sendConfirmMail(String email) throws Exception {
		String random = new TempKey().getKey(8, false);  // 인증키 생성
		
		removeConfirmCode(email);
		String title = "TRABLOCK 회원가입 인증 코드입니다.";
		String content = "\n\n안녕하세요! 트래블록(TraBlock)을 찾아주셔서 감사합니다.\n\n 인증코드 : " + random; // 내용
          
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	    messageHelper.setTo(email); // 받는사람 이메일
	    messageHelper.setSubject(title); // 메일제목
	    messageHelper.setText(content); // 메일 내용
	      
	    mailSender.send(message);
	      
	    //인증키 등록
	    userRepository.insertEmailConfirm(email, random);//유효시간 3분
	}
	

	//인증키 확인
	@Override
	public boolean checkConfirmCode(EmailConfirm emailConfirm) {
		int r = userRepository.checkConfirmCode(emailConfirm);
		if(r > 0) {
			removeConfirmCode(emailConfirm.getEmail());
			return true;
		}
		return false;
	}

	
	//인증키 삭제
	@Override
	public void removeConfirmCode(String email) {
		userRepository.deleteConfirmCode(email);
	}


	@Override
	public boolean isDupNickname(String nickname) {
		return userRepository.isDupNickname(nickname) > 0 ? true : false;
	}


	@Override
	public boolean checkPassword(String uid, String password) {
		return userRepository.checkPassword(uid, password) > 0 ? true : false;
	}


	@Override
	public void sendTmpPasswordEmail(String password, String email) {
		String title = "TRABLOCK 임시 비밀번호 발급";
		String content = "\n\n안녕하세요!, 임시 비밀번호로 로그인 후 반드시 수정해주세요!!"
						+ "\n\n새 비밀번호 : " + password; // 내용
            
		userRepository.updatePasswordByEmail(email, SHA256.testSHA256(password));
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	        messageHelper.setTo(email); // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목
	        messageHelper.setText(content); // 메일 내용
	        
	        mailSender.send(message);
		}catch(MessagingException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}
}
