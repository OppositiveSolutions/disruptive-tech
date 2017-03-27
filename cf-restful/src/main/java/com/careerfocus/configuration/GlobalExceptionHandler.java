package com.careerfocus.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = RuntimeException.class)
	public String handleBaseException(RuntimeException e) {
		return "12 " + e.getMessage();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public String handleNoHandlerFoundException(Exception e) {
		
		return e.getClass().getName() + " 16" + e.getMessage();
	}
	
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		
		return e.getClass().getName() + " 14" + e.getMessage();
	}
	
}
