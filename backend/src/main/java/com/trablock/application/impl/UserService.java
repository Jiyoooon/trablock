package com.trablock.application.impl;

import java.util.List;

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

//    @Override
//    public User get(long id) {
//    	//다른 유저 정보 가져옴
//        return this.userRepository.get(id);
//    }
//
//    @Override
//    public User get(String email) {
//    	//로그인한 유저 정보 가져옴 => token
//    	return this.userRepository.get(email); 
//    }
//
//    @Override
//    public User add(User user) {
//        long id = this.userRepository.create(user);
//        return this.userRepository.get(id);
//    }
//
//    @Override
//    public User update(User user) {
//
//        User found = this.userRepository.get(user.getEmail());
//        if(found == null)
//            throw new ApplicationException("회원 정보를 찾을 수 없습니다.");
//
//        if(user.getId() == 0)
//            user.setId(found.getId());
//        if(user.getName() == null)
//            user.setName(found.getName());
//        if(user.getPassword() == null)
//            user.setPassword(found.getPassword());
//
//        int affected = this.userRepository.update(user);
//        if(affected == 0)
//            throw new ApplicationException("회원수정 처리가 반영되지 않았습니다.");
//
//        return this.userRepository.get(user.getId());
//    }

    @Override
    public void delete(long id) {
        this.userRepository.delete(id);
    }


	@Override
	public boolean isDupEmail(String email) {
		return userRepository.isDupEmail(email) > 0 ? true : false;
	}


	@Override
	public boolean isConfirmedEmail(String email) {
		return userRepository.isConfirmedEmail(email) > 0 ? true : false;
	}


	@Override
	public void sendConfirmMail(String email) throws Exception {
		String random = new TempKey().getKey(8, false);  // 인증키 생성
		
		removeConfirmCode(email);
		String title = "TRABLOCK 회원가입 인증 코드입니다.";
		String content = "\n\n안녕하세요! 트래블록(TraBlock)을 찾아주셔서 감사합니다.\n\n 인증코드 : " + random; // 내용
          
      try {
          MimeMessage message = mailSender.createMimeMessage();
          MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
          messageHelper.setTo(email); // 받는사람 이메일
          messageHelper.setSubject(title); // 메일제목
          messageHelper.setText(content); // 메일 내용
          
          mailSender.send(message);
          addConfirmedEmail(email, random);//유효시간 3분
          
      } catch (Exception e) {
    	  e.printStackTrace();
    	  throw new Exception("서버 오류");
      }
	}
	
	public int addConfirmedEmail(String email, String code) {
		return userRepository.insertEmailConfirm(email, code);
		
	}

	
	
	@Override
	public boolean checkConfirmCode(EmailConfirm emailConfirm) {
		return userRepository.checkConfirmCode(emailConfirm) > 0 ? true : false;
	}

	@Override
	public void removeConfirmCode(String email) {
		userRepository.deleteConfirmCode(email);
	}


	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User get(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User add(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
