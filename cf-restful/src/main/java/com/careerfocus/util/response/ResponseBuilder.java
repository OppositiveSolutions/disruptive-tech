package com.careerfocus.util.response;

import java.util.List;

public abstract class ResponseBuilder {

	protected static ResponseBuilder newInstance() {
        ResponseBuilder b = new ResponseBuilderImpl();
        return b;
    }
	
	public abstract ResponseBuilder ok();
	
	public abstract ResponseBuilder ok(Object data);
	
	public abstract ResponseBuilder ok(Object data, String message);
	
//	public abstract ResponseBuilder ok(String message);
	
	public abstract ResponseBuilder status(int status);
	
	public abstract ResponseBuilder data(Object data);
	
	public abstract ResponseBuilder message(String message);
	
	public abstract ResponseBuilder error(ResponseError error);
	
	public abstract ResponseBuilder error(Error error, List<Error> subErrors);
	
	public abstract ResponseBuilder unauthorized();
	
	public abstract Response build();
	
	
	
}
