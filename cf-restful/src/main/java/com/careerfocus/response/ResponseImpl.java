package com.careerfocus.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseImpl extends Response {

	private boolean status;

	private Object data;

	private String message;

	private ResponseError error;
	
	
	protected ResponseImpl(boolean status, Object data, String message, ResponseError error) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.error = error;
    }

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseError getError() {
		return error;
	}

	public void setError(ResponseError error) {
		this.error = error;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public boolean getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toJsonString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
