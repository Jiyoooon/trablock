package com.trablock.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trablock.application.IJwtService;
import com.trablock.application.IUserService;
import com.trablock.domain.EmailConfirm;
import com.trablock.domain.Login;
import com.trablock.domain.PartyMember;
import com.trablock.domain.TempKey;
import com.trablock.domain.User;
import com.trablock.domain.exception.BadRequestException;
import com.trablock.domain.exception.DomainException;
import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;
import com.trablock.util.SHA256;

import io.swagger.annotations.ApiOperation;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;
    
    @Autowired
    private IJwtService jwtService;
    
    @Autowired
	private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserController(IUserService userService) {
        Assert.notNull(userService, "userService 개체가 반드시 필요!");
        this.userService = userService;
    }

    //이메일 중복 체크 
  	@ApiOperation(value = "이메일 중복 체크", notes = "fail : 중복되는 이메일 있음 | success : 중복되는 이메일 없음")
  	@GetMapping("user/dup/email/{email}")
  	public ResponseEntity<HashMap<String, Object>> signupEmailCheck(@PathVariable("email") String email) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		
		if(userService.isDupEmail(email)) {//이미 존재하는 계정
			throw new BadRequestException("이미 가입한 계정");
		}else {
			map.put("result", "success");
		}
  	
  		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  	}
  	
  	//이메일 인증키 발송
  	@ApiOperation(value = "인증키 발송 요청")
  	@GetMapping("user/verification/{email}")
  	public ResponseEntity<HashMap<String, Object>> signupSendCheckEmail(@PathVariable("email") String email) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		
  		//email이 중복됐는지 확인
  		if(userService.isDupEmail(email)) {
  			throw new BadRequestException("이미 가입한 계정");
  		}
  		//인증코드 한번만 보낼 수 있게
  		if(userService.isConfirmedEmail(email)) {
  			throw new BadRequestException("인증코드 발송함");
  		}
  		
  		userService.sendConfirmMail(email);
  		
        map.put("result", "success");
  		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  	}
  	
  	//인증키 확인
  	@ApiOperation(value = "인증키 확인 요청")
  	@PostMapping("user/verification")
  	public ResponseEntity<HashMap<String, Object>> signupVerification(@RequestBody EmailConfirm emailConfirm) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		
  		if(userService.checkConfirmCode(emailConfirm)) {
  			map.put("result", "success");
  			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  		}else {
          	map.put("result", "fail");
              return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
          }
          
  	}
  	
  	//닉네임 중복체크
  	@ApiOperation(value = "닉네임 중복 체크", notes = "fail : 중복되는 닉네임 있음 | success : 중복되는 닉네임 없음")
  	@GetMapping("user/dup/nickname/{nickname}")
  	public ResponseEntity<HashMap<String, Object>> signupNicknameCheck(@PathVariable("nickname") String nickname) throws Exception {
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		if(userService.isDupNickname(nickname)) {//이미 존재하는 닉네임
  			map.put("result", "fail");
  			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  		}else {
  			map.put("result", "success");
  			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
  		}
  	}
    
  	
    //전체 유저 리스트
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> list(HttpServletResponse response) {
        List<User> userList = userService.list();
		
        if (userList == null || userList.isEmpty() )
            throw new EmptyListException("NO DATA");

        return userList;
    }

    
    //회원가입
  	@ApiOperation(value = "회원가입", notes = "회원가입 후 'jwt-token'으로 access token 넘겨줌")
  	@PostMapping(value = "user/join")
  	public ResponseEntity<HashMap<String, Object>> signupUser2(@ModelAttribute("user") User user, HttpServletResponse response)throws Exception {
      	HashMap<String, Object> map = new HashMap<String, Object>();
      	
      	System.out.println(user);
//      	String namePt = "^[a-zA-Z0-9가-힣]{2,12}$";
      	String pwPt = "^[0-9a-zA-Z~`!@#$%\\\\^&*()-]{8,12}$";//특수,대소문자,숫자 포함 8자리 이상
      	String emailPt = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
      	
      	map.put("result", "fail");
      	if(user.getNickname() == null || user.getNickname().equals("")) {
      		map.put("cause", "닉네임 입력 필수");
      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
      	}
      	if(userService.isDupNickname(user.getNickname())) {
      		map.put("cause", "닉네임 중복");
      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
      	}
      	
      	
      	if(user.getPassword() == null || user.getPassword().equals("")) {
      		map.put("cause", "비밀번호 입력 필수");
      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
      	}
//      	if(!user.getPassword().matches(pwPt)) {
//      		map.put("cause", "비밀번호 형식 오류");
//      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
//      	}
      	
      	
      	if(user.getEmail() == null || user.getEmail().equals("")) {
      		map.put("cause", "이메일 입력 필수");
      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
      	}
//      	if(!user.getEmail().matches(emailPt)) {
//      		map.put("cause", "이메일 형식 오류");
//      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
//      	}
      	if(userService.isDupEmail(user.getEmail())) {
      		map.put("cause", "이메일 중복");
      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
      	}
      	
      	response.setHeader("Access-Control-Allow-Headers", "jwt-token");//token
      	
      	User me = userService.add(user);
  		
      	if(me.getId() > 0) {
      		String token = jwtService.create(Long.toString(me.getId()));
      		map.put("result", "success");
      		map.put("data", me);
      		response.addHeader("jwt-token", token);
      		
      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
      	}else {
      		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
      	}
  	}
      
  	 //로그인
     @ApiOperation(value = "로그인", notes = "로그인 후 'jwt-token'으로 access token 넘겨줌")
     @PostMapping("user/login")
     public ResponseEntity<HashMap<String, Object>> signinUser(@RequestBody Login login
     								, HttpServletResponse response) throws Exception {
	      HashMap<String, Object> map = new HashMap<String, Object>();
	      HttpStatus status = null;
	      	
	      response.setHeader("Access-Control-Allow-Headers", "jwt-token");//token
	      	System.out.println(login.getEmail()+", "+login.getPassword());
		  User user = userService.getUserInfo(login.getEmail());
	  	  if (user == null) {
	  		  throw new NotFoundException("회원 정보 찾을 수 없음");
		  } else {
			  if (!SHA256.testSHA256(login.getPassword()).equals(user.getPassword()))
				  throw new NotFoundException("비밀번호 불일치");
			  user.setPassword("");
			  String token = jwtService.create(Long.toString(user.getId()));
			  map.put("result", "success");
			  map.put("data", user);
			  response.addHeader("jwt-token", token);
		  }
	  	  
	  	  status = HttpStatus.ACCEPTED;
	  	  return new ResponseEntity<HashMap<String, Object>>(map, status);
     }
      
     
      //로그아웃
      @ApiOperation(value = "로그아웃", notes = "Authorization header => 'Bearer [token]'")///token
      @GetMapping("/token/user/logout")
	     	public ResponseEntity<HashMap<String, Object>> signoutUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	String result = "success";
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	
	      	String token = request.getHeader("Authorization").split(" ")[1];
	      	
			//access token을 blacklist로
			redisTemplate.opsForValue().set(token, true);
			redisTemplate.expire(token, 10, TimeUnit.DAYS);//10일 후 보관 종료
	
			
			
	  		map.put("result", result);
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
     	}
      
      //회원탈퇴
      @ApiOperation(value = "회원탈퇴", notes = "Authorization header => 'Bearer [token]'")///token
      @DeleteMapping("/token/user")
      public ResponseEntity<HashMap<String, Object>> deleteUser(HttpServletRequest request) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	String result = "success";
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	
	      	String token = request.getHeader("Authorization").split(" ")[1];
	
	  		Map<String, Object> claims = jwtService.get(token);
	  		String uid = (String)claims.get("uid");
	  		userService.delete(uid);
	  		
	  		map.put("result", result);
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
      }
      
      //회원정보 조회
      @ApiOperation(value = "내 정보 가져오기", notes = "Authorization header => 'Bearer [token]'")///token
      @GetMapping("/token/user")
      public ResponseEntity<HashMap<String, Object>> getUserInfo(HttpServletRequest request) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	
	      	
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	map.put("result", "success");
	      	
	      	String token = request.getHeader("Authorization").split(" ")[1];
	
	  		Map<String, Object> claims = jwtService.get(token);
	  		String uid = (String)claims.get("uid");
	  		
  			User user = userService.getMyInfo(Long.parseLong(uid));
  			map.put("data", user);
	  			
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
      }
     
      //id로 회원정보 조회
      @ApiOperation(value = "id로 회원 정보 가져오기")
      @GetMapping("user/id/{id}")
      public ResponseEntity<HashMap<String, Object>> getUserInfoById(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	String result = "fail";
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      
  			User user = userService.getUserInfo(Long.parseLong(id));
  			if(user == null) {
  				logger.error("NOT FOUND ID: ", id);
  				throw new NotFoundException("회원 정보 찾을 수 없음");
  			}else {
  				user.setPassword("");
  				result = "success";
  				map.put("data", user);
  			}
  			
  			map.put("result", result);
  			return new ResponseEntity<HashMap<String, Object>>(map, status);
      }
      
      
      //닉네임 or 이메일로 회원 조회
      @ApiOperation(value = "닉네임 또는 이메일로 회원 정보 가져오기")
      @GetMapping("user/query/{query}")
      public ResponseEntity<HashMap<String, Object>> getUserInfoByEmailOrNickname(HttpServletRequest request, @PathVariable("query") String query) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	String result = "fail";
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      
  			List<User> userList = userService.findUserByEmailOrNickname(query);
  			if(userList == null) {
  				logger.error("NOT FOUND Email or Nickname: ", query);
  				throw new NotFoundException("회원 정보 찾을 수 없음");
  			}else {
  				result = "success";
  				map.put("data", userList);
  			}
  			
  			map.put("result", result);
  			return new ResponseEntity<HashMap<String, Object>>(map, status);
      }
      
      
      //회원정보 수정
      @ApiOperation(value = "회원정보 수정하기", notes = "Authorization header => 'Bearer [token]'")///token
      @PutMapping("/token/user")
      public ResponseEntity<HashMap<String, Object>> reviseUser(@ModelAttribute("user") User user, HttpServletRequest request) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	
	      	String token = request.getHeader("Authorization").split(" ")[1];
	
	  		Map<String, Object> claims = jwtService.get(token);
	  		user.setId(Long.parseLong((String)claims.get("uid")));
	  		
	  		userService.update(user);
	  		map.put("result", "success");
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
      }

      
      //비밀번호 확인
      @ApiOperation(value = "비밀번호 확인", notes = "- Authorization header => 'Bearer [token]'\n"
      											+ "- {'passowrd': '~~'} 형식으로 requestbody 보내주세요")///token
      @PostMapping("/token/user/password")
      public ResponseEntity<HashMap<String, Object>> checkPassword(@RequestBody Map<String, Object> param, HttpServletRequest request) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	String password = (String)param.get("password");
	      	String result = "success";
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	
	      	String token = request.getHeader("Authorization").split(" ")[1];
	  		Map<String, Object> claims = jwtService.get(token);
	  		
	  		
  			if(!userService.checkPassword((String)claims.get("uid"), SHA256.testSHA256(password))) {
  				result = "fail";
  				map.put("cause", "비밀번호 불일치");
  			}
	  		
	  		map.put("result", result);
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
      }
      
      //비밀번호 찾기
      @ApiOperation(value = "비밀번호 찾기")
      @GetMapping("/user/password/{email}")
      public ResponseEntity<HashMap<String, Object>> sendEmailForPw(@PathVariable("email") String email, HttpServletRequest request) throws Exception {
	  		HashMap<String, Object> map = new HashMap<String, Object>();
	  		
	  		if(!userService.isDupEmail(email)) {
	  			throw new BadRequestException("가입하지 않은 회원");
	  		}
	  		
	  		String tmpPw = new TempKey().getKey(6, false);  // 임시비밀번호
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	
	  		userService.sendTmpPasswordEmail(tmpPw, email);
	  				
	        map.put("result", "success");
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
     }
      
      //비밀번호 찾기
      @ApiOperation(value = "모임별 유저 정보", notes = "Authorization header => 'Bearer [token]'")
      @GetMapping("/token/user/party/{partyId}")
      public ResponseEntity<HashMap<String, Object>> getUserInParty(@PathVariable("partyId") String partyId, HttpServletRequest request) throws Exception {
	  		HashMap<String, Object> map = new HashMap<String, Object>();
	  		
	  		List<PartyMember> partyUser = userService.partyUserList(Long.parseLong(partyId));
	  		//내가 포함되어있는 모임만 조회 가능..
	  		
	  		String token = request.getHeader("Authorization").split(" ")[1];
	  		Map<String, Object> claims = jwtService.get(token);
	  		
	  		long uid = Long.parseLong((String)claims.get("uid"));
	  		
	  		if(partyUser.size() == 0) {
	  			throw new BadRequestException("모임을 찾을 수 없음");
	  		}
	  		
	  		boolean isMyParty = false;
	  		for(PartyMember pm : partyUser) {
	  			if(pm.getUserId() == uid) {
	  				isMyParty = true;
	  				break;
	  			}
	  		}
	  		
	  		if(!isMyParty) {
	  			throw new BadRequestException("다른 모임 조회 불가능");
	  		}
	  		
	        map.put("result", "success");
	        map.put("data", partyUser);
	        
	        
	  		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.ACCEPTED);
     }
    

}
