package com.trablock.api;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trablock.application.IJwtService;
import com.trablock.application.IMemoService;
import com.trablock.domain.Memo;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/token")
public class MemoController {
    public static final Logger logger = LoggerFactory.getLogger(MemoController.class);

    private IMemoService memoService;
    
    @Autowired
    private IJwtService jwtService;
    

    @Autowired
    public MemoController(IMemoService memoService) {
        Assert.notNull(memoService, "memoService 개체가 반드시 필요!");
        this.memoService = memoService;
    }

    //메모 작성
    @ApiOperation(value = "새로운 메모 작성", notes = "Authorization header => 'Bearer [token]'")
    @PostMapping(path = "/memo")
    public ResponseEntity<HashMap<String, Object>> writeMemo(@ModelAttribute("memo") Memo memo
    														, HttpServletRequest request) throws Exception{
    	HashMap<String, Object> map = new HashMap<>();
    	HttpStatus status = HttpStatus.ACCEPTED;
    	
    	
    	String userId = getLoginId(request);
    	
    	map.put("result", "success");
    	memoService.registeMemo(memo, Long.parseLong(userId));
    	
    	return new ResponseEntity<HashMap<String, Object>>(map, status);
    }
    
    
    //메모 수정
    @ApiOperation(value = "메모 수정", notes = "Authorization header => 'Bearer [token]'")
    @PutMapping(path = "/memo")
    public ResponseEntity<HashMap<String, Object>> updateMemo(@ModelAttribute("memo") Memo memo
    														, HttpServletRequest request) throws Exception{
    	HashMap<String, Object> map = new HashMap<>();
    	HttpStatus status = HttpStatus.ACCEPTED;
    	
    	String userId = getLoginId(request);
    	
    	map.put("result", "success");
    	map.put("data", memoService.updateMemo(memo, Long.parseLong(userId)));
    	
    	return new ResponseEntity<HashMap<String, Object>>(map, status);
    }
    
    //메모 삭제
    @ApiOperation(value = "메모 삭제", notes = "Authorization header => 'Bearer [token]'")
    @DeleteMapping(path = "/memo/{id}")
    public ResponseEntity<HashMap<String, Object>> removeMemo(@PathVariable("id") long memoId
    														, HttpServletRequest request) throws Exception{
    	HashMap<String, Object> map = new HashMap<>();
    	HttpStatus status = HttpStatus.ACCEPTED;
    	
    	
    	String userId = getLoginId(request);
    	
    	map.put("result", "success");
    	memoService.removeMemo(memoId, Long.parseLong(userId));
    	
    	return new ResponseEntity<HashMap<String, Object>>(map, status);
    }
    
    //id로 메모 조회
    @ApiOperation(value = "memoid로 메모 조회", notes = "Authorization header => 'Bearer [token]'")
    @GetMapping(path = "/memo/{memoId}")
    public ResponseEntity<HashMap<String, Object>> getMemoById(@PathVariable("memoId") long memoId
    														, HttpServletRequest request) throws Exception{
    	HashMap<String, Object> map = new HashMap<>();
    	HttpStatus status = HttpStatus.ACCEPTED;
    	
    	
    	String userId = getLoginId(request);
    	
    	map.put("result", "success");
    	map.put("data", memoService.searchMemoById(memoId, Long.parseLong(userId)));
    	
    	return new ResponseEntity<HashMap<String, Object>>(map, status);
    }
    
    //모임별 메모 조회
    @ApiOperation(value = "모임별 메모 조회", notes = "Authorization header => 'Bearer [token]'")
    @GetMapping(path = "/memos/{partyId}")
    public ResponseEntity<HashMap<String, Object>> getMemosByParty(@PathVariable("partyId") long partyId
    														, HttpServletRequest request) throws Exception{
    	HashMap<String, Object> map = new HashMap<>();
    	HttpStatus status = HttpStatus.ACCEPTED;
    	
    	
    	String userId = getLoginId(request);
    	
    	map.put("result", "success");
    	map.put("data", memoService.searchMemoByParty(partyId, Long.parseLong(userId)));
    	
    	return new ResponseEntity<HashMap<String, Object>>(map, status);
    }

    
    public String getLoginId(HttpServletRequest request) {
    	String token = request.getHeader("Authorization").split(" ")[1];
    	return (String)jwtService.get(token).get("uid");
    }
}
