package com.careerfocus.model.response;

import com.careerfocus.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class StudentVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3658306710126419002L;

    private int userId;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_TIME_FORMAT, timezone = DateUtils.PRIMARY_TIMEZONE)
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_FORMAT, timezone = DateUtils.PRIMARY_TIMEZONE)
    private Date expiryDate;

    private String emailId;

    private String phone;

    private int status;

    private int centerId;
    
    private int type;

    public StudentVO(int userId, String name, Date createdDate, Date expiryDate, String emailId, String phone, int status, int centerId, int type) {
        super();
        this.userId = userId;
        this.name = name;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.emailId = emailId;
        this.phone = phone;
        this.status = status;
        this.centerId = centerId;
        this.setType(type);
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

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
