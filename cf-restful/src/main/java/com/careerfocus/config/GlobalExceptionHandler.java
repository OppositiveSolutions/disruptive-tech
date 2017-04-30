package com.careerfocus.config;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.careerfocus.exception.UnauthorisedException;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	
	private static int ERROR_INVALID_CREDENTIALS_CODE = 49;
	
	private static String ERROR_INVALID_CREDENTIALS_MSG = "The username or password you've entered is incorrect.";

	private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = RuntimeException.class)
	public Response handleBaseException(RuntimeException e) {
		log.error("Error", e);
		Error error = new Error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name());
		return Response.status(HttpStatus.BAD_REQUEST.value()).error(error, null).build();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public Response handleNoHandlerFoundException(Exception e) {
		log.error("Error", e);
		Error error = new Error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
		return Response.status(HttpStatus.NOT_FOUND.value()).error(error, null).build();
	}

	@ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
	public Response handleException(AuthenticationCredentialsNotFoundException e) {		
		log.error("Error", e);
		Error error = new Error(ERROR_INVALID_CREDENTIALS_CODE, ERROR_INVALID_CREDENTIALS_MSG);
		return Response.status(ERROR_INVALID_CREDENTIALS_CODE).error(error, null).build();
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = UnauthorisedException.class)
	public Response handleNotAuthorizedExceptionException(UnauthorisedException e) {		
//		log.error("Error", e);
		return Response.unauthorized().build();
	}
	
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		log.error("Error", e);
		return e.getClass().getName() + " 14" + e.getMessage();
	}
	

}
