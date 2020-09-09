package com.trablock.api;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trablock.application.IUserService;
import com.trablock.domain.EmailConfirm;
import com.trablock.domain.User;
import com.trablock.domain.exception.DomainException;
import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        Assert.notNull(userService, "userService 개체가 반드시 필요!");
        this.userService = userService;
    }

    //이메일 중복 체크 
  	@ApiOperation(value = "이메일 중복 체크", notes = "fail : 중복되는 이메일 있음 | success : 중복되는 이메일 없음")
  	@GetMapping("/dup/{email}")
  	public ResponseEntity<HashMap<String, Object>> signupEmailCheck(@PathVariable("email") String email) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		
  		try {
  			if(userService.isDupEmail(email)) {//이미 존재하는 계정
  				map.put("result", "fail");
  				map.put("cause", "이미 존재하는 계정");
  			}else {
  				map.put("result", "success");
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  			throw new Exception("서버 오류");
//  			map.put("reult", "fail");
//  			map.put("cause", "서버 오류");
//  			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  	}
  	
  	//이메일 인증키 발송
  	@ApiOperation(value = "인증키 발송 요청")
  	@GetMapping("/verification/send/{email}")
  	public ResponseEntity<HashMap<String, Object>> signupSendCheckEmail(@PathVariable("email") String email) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		
  		map.put("result", "fail");
  		//email이 중복됐는지 확인
  		if(userService.isDupEmail(email)) {
  			map.put("cause", "이미 가입한 계정");
  			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  		}
  		
  		//인증코드 한번만 보낼 수 있게
  		if(userService.isConfirmedEmail(email)) {
  			map.put("cause", "인증코드 발송함");
  			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  		}
  		
  		userService.sendConfirmMail(email);
  		
//  		userService.removeConfirmCode(email);
//  		String title = "쿠킷 회원가입 인증 코드입니다.";
//  		String content = "\n\n안녕하세요 회원님, 쿠킷(Cookit)을 찾아주셔서 감사합니다.\n\n 인증코드 : " + random; // 내용
//              
//          try {
//              MimeMessage message = mailSender.createMimeMessage();
//              MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//              messageHelper.setTo(email); // 받는사람 이메일
//              messageHelper.setSubject(title); // 메일제목
//              messageHelper.setText(content); // 메일 내용
//              
//              mailSender.send(message);
//              userService.addEmailConfirm(email, random);//유효시간 3분
//              
//          } catch (Exception e) {
//              e.printStackTrace();
//              map.put("cause", "서버 오류");
//              return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.SERVICE_UNAVAILABLE);
//          }
  		
          map.put("result", "success");
  		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  	}
  	
  	//인증키 확인
  	@ApiOperation(value = "인증키 확인 요청")
  	@PostMapping("/verification/check")
  	public ResponseEntity<HashMap<String, Object>> signupVerification(@RequestBody EmailConfirm emailConfirm) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		
  		if(userService.checkConfirmCode(emailConfirm)) {
  			map.put("result", "success");
  			userService.removeConfirmCode(emailConfirm.getEmail());
  			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  		}else {
          	map.put("result", "fail");
              return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
          }
          
  	}
    
    //전체 유저 리스트
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> list() {
        List<User> userList = userService.list();

        if (userList == null || userList.isEmpty() )
            throw new EmptyListException("NO DATA");

        return userList;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable int id) {

        User user = userService.get(id);
        if (user == null) {
            logger.error("NOT FOUND ID: ", id);
            throw new NotFoundException(id + " 회원 정보를 찾을 수 없습니다.");
        }

        return user;
    }

    
    //로그인
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
        User userFetched = userService.get(user.getEmail());
        if (!userFetched.getPassword().equals(user.getPassword()))
            throw new DomainException("비밀번호가 일치하지 않습니다.");
        userFetched.setPassword("");
        return userFetched;
    }

    
    //회원가입
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return userService.add(user);
    }

    
    //회원수정
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    
    //회원탈퇴
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
    
    //비번 확인
    //모임별 유저 정보 get
    //

}
