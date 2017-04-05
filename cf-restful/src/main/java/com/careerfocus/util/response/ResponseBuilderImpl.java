package com.careerfocus.util.response;

import java.util.List;

import com.careerfocus.util.response.Response.Status;

public final class ResponseBuilderImpl extends ResponseBuilder {

	private int status;

	private Object data;

	private String message;

	private ResponseError error;
	
	@Override
	public ResponseBuilder status(int status) {
		this.status = status;
		return this;
	}

	@Override
	public ResponseBuilder data(Object data) {
		this.data= data;
		return this;
	}
	
	@Override
	public ResponseBuilder message(String message) {
		this.message= message;
		return this;
	}
	
	@Override
	public Response build() {
        final Response r = new ResponseImpl(status, data, message, error);
        reset();
        return r;
    }
	
	private void reset() {
        status = Status.OK.getStatusCode();
        data = null;
        message = null;
        error = null;
    }

	@Override
	public ResponseBuilder ok() {
		this.status = Status.OK.getStatusCode();
		return this;
	}

	@Override
	public ResponseBuilder ok(Object data) {
		this.status = Status.OK.getStatusCode();
		this.data = data;
		return this;
	}

	@Override
	public ResponseBuilder ok(Object data, String message) {
		this.status = Status.OK.getStatusCode();
		this.data = data;
		this.message = message;
		return this;
	}

//	@Override
//	public ResponseBuilder ok(String message) {
//		this.status = true;
//		this.message = message;
//		return this;
//	}

	@Override
	public ResponseBuilder error(ResponseError error) {
		this.error = error;
		return this;
	}

	@Override
	public ResponseBuilder error(Error error, List<Error> subErrors) {
		this.error = new ResponseError(error, subErrors);
		return this;
	}

	@Override
	public ResponseBuilder unauthorized() {
		this.status = 401;
		this.error = new ResponseError(new Error(401, "Invalid authentication"), null);
		return null;
	}
	
}
