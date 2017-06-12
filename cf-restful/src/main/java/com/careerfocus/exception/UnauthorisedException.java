package com.careerfocus.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;


public class UnauthorisedException extends AuthenticationCredentialsNotFoundException {

    public UnauthorisedException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
