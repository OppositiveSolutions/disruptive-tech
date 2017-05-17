package com.careerfocus.model.response;

import java.io.Serializable;
import java.util.Date;

import com.careerfocus.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3658306710126419002L;
	
	int userId;

	String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_TIME_FORMAT, timezone=DateUtils.PRIMARY_TIMEZONE)
	Date createdDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_FORMAT, timezone=DateUtils.PRIMARY_TIMEZONE)
	Date expiryDate;
	
	String emailId;
	
	String phone;
	
	int status;
	
	public StudentVO(int userId, String name, Date createdDate, Date expiryDate, String emailId, String phone, int status) {
		super();
		this.userId = userId;
		this.name = name;
		this.createdDate = createdDate;
		this.expiryDate = expiryDate;
		this.emailId = emailId;
		this.phone = phone;
		this.status = status;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
