package com.careerfocus.util.response;

import java.util.List;

public class ResponseError extends Error {

	List<Error> details;
	
	public ResponseError(Error error, List<Error> details) {
		super(error.code, error.message);
		this.details = details;
	}
	
	
}
