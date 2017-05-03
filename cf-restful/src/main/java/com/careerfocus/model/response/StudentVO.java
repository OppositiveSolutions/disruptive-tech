package com.careerfocus.model.response;

import java.io.Serializable;
import java.util.Date;

public class StudentVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3658306710126419002L;

	String name;
	
	Date createdDate;
	
	Date expiryDate;
	
	String emailId;
	
	String phone;
	
	int status;
	
	public StudentVO(String name, Date createdDate, Date expiryDate, String emailId, String phone, int status) {
		super();
		this.name = name;
		this.createdDate = createdDate;
		this.expiryDate = expiryDate;
		this.emailId = emailId;
		this.phone = phone;
		this.status = status;
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
