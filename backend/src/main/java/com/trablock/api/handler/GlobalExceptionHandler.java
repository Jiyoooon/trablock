package com.trablock.api.handler;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trablock.domain.exception.BadRequestException;
import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public HashMap<String, Object> handleNotFoundException(NotFoundException e) {
//    	e.printStackTrace();
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(value = EmptyListException.class)
    public HashMap<String, Object> handleEmptyListException(EmptyListException e) {
//    	e.printStackTrace();
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }

    //token 존재여부, 유효성 체크 exception 처리
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = SecurityException.class)
    public HashMap<String, Object> unAuthorizedExceptionHandler(Exception e){
//    	e.printStackTrace();
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public HashMap<String, Object> badRequestHandleException(Exception e){
//    	e.printStackTrace();
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public HashMap<String, Object> handleException(Exception e){
    	e.printStackTrace();
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }
    
    
    
    
}
