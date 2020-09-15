package com.trablock.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.NotFoundException;
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
import com.trablock.application.IPostService;
import com.trablock.application.IUserService;
import com.trablock.domain.Post;
import com.trablock.domain.exception.BadRequestException;
import com.trablock.domain.exception.EmptyListException;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/token")
public class PostController {
    public static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private IPostService postService;
    
    @Autowired
    private IJwtService jwtService;
  
//    @Autowired
//    public PostController(IPostService postService) {
//        Assert.notNull(postService, "postService 개체가 반드시 필요!");
//        this.postService = postService;
//    }
    
    
    //포스팅 작성
    @ApiOperation(value = "포스팅 작성", notes = "Authorization header => 'Bearer <token>'")///token
    @PostMapping("/post")
    public ResponseEntity<HashMap<String, Object>> registePost(HttpServletRequest request, @ModelAttribute("post") Post post) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	map.put("result", "success");
	      	
	      	String token = request.getHeader("Authorization").split(" ")[1];
	
	  		Map<String, Object> claims = jwtService.get(token);
	  		String uid = (String)claims.get("uid");
	  		post.setUserId(Long.parseLong(uid));
	  		
	  		postService.registePost(post);
	  		
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
    }
    
    //id로 포스팅 조회
    @ApiOperation(value = "id로 포스팅 조회", notes = "Authorization header => 'Bearer <token>'")///token
    @GetMapping("/post/{postId}")
    public ResponseEntity<HashMap<String, Object>> searchPostById(HttpServletRequest request, @PathVariable("postId") long postId) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	map.put("result", "success");
	      	
	      	Post post = postService.searchPostById(Long.toString(postId));

	      	if(post == null) {
	      		throw new NotFoundException("포스팅 찾을 수 없음");
	      	}
	      	
	      	map.put("data", post);
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
    }
    
    
    //포스팅 검색
    @ApiOperation(value = "포스팅 검색", notes = 	"Authorization header => 'Bearer <token>'\n"
    											+ "type => 0 : 전체포스팅, 1 : 내가 쓴 포스팅, 2 : 내 모임 포스팅")///token
    @GetMapping("/posts")
    public ResponseEntity<HashMap<String, Object>> searchPosts(HttpServletRequest request
    															, String title
    															, String destination
    															, String range_start
    															, String range_end
    															, String type) throws Exception {
	      	HashMap<String, Object> map = new HashMap<String, Object>();
	      	
	      	HttpStatus status = HttpStatus.ACCEPTED;
	      	map.put("result", "success");
	      	
	      	String token = request.getHeader("Authorization").split(" ")[1];
	    	
	  		Map<String, Object> claims = jwtService.get(token);
	  		String userId = (String)claims.get("uid");
	      	 
	      	List<Post> post = postService.searchPosts(title, destination, range_start, range_end, userId, type);

//	      	if(post.size() == 0) {
//	      		throw new EmptyListException("조건에 맞는 포스팅이 없음");
//	      	} 
	      	
	      	map.put("data", post);
	  		return new ResponseEntity<HashMap<String, Object>>(map, status);
    }
    
     
    //포스팅 수정
    @ApiOperation(value = "포스팅 수정", notes = 	"Authorization header => 'Bearer <token>'")///token
	@PutMapping("/post")
	public ResponseEntity<HashMap<String, Object>> updatePost(HttpServletRequest request, @ModelAttribute("post") Post post) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		HttpStatus status = HttpStatus.ACCEPTED;
		map.put("result", "success");
		
		String token = request.getHeader("Authorization").split(" ")[1];
		
		Map<String, Object> claims = jwtService.get(token);
		String userId = (String)claims.get("uid");
		
		//내가 쓴 글만 수정 가능
		if(post.getUserId() != Long.parseLong(userId)) {
			throw new BadRequestException("본인 글만 수정 가능");
		}
		
		postService.updatePost(post);
		return new ResponseEntity<HashMap<String, Object>>(map, status);
	}
    
    
    //포스팅  삭제
    @ApiOperation(value = "포스팅 삭제", notes = 	"Authorization header => 'Bearer <token>'")///token
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<HashMap<String, Object>> deletePost(HttpServletRequest request, @PathVariable("postId") String postId) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		HttpStatus status = HttpStatus.ACCEPTED;
		map.put("result", "success");
		
		String token = request.getHeader("Authorization").split(" ")[1];
		
		Map<String, Object> claims = jwtService.get(token);
		String userId = (String)claims.get("uid");
		
		//내가 쓴 글만 삭제 가능
		if(postService.removePost(postId, userId) == 0) {
			throw new BadRequestException("본인 글만 삭제 가능");
		}
		return new ResponseEntity<HashMap<String, Object>>(map, status);
	}
    

}
