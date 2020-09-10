package com.trablock.api.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public HashMap<String, Object> handleNotFoundException(NotFoundException e) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(value = EmptyListException.class)
    public HashMap<String, Object> handleEmptyListException(EmptyListException e) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }

    //token 존재여부, 유효성 체크 exception 처리
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = SecurityException.class)
    public HashMap<String, Object> unAuthorizedExceptionHandler(Exception e){
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public HashMap<String, Object> handleException(Exception e){
    	HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "fail");
		map.put("cause", e.getMessage());
		
		return map;
    }
    
    
}
