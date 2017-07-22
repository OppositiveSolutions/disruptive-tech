package com.careerfocus.model.response;

import com.careerfocus.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class StaffVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3658306710126419002L;

    private int userId;

    private String name;

    private String salary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_FORMAT, timezone = DateUtils.PRIMARY_TIMEZONE)
    private Date joiningDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_FORMAT, timezone = DateUtils.PRIMARY_TIMEZONE)
    private Date createdDate;

    private String emailId;

    private String phone;

    private int status;

    public StaffVO(int userId, String name, Date joiningDate, Date createdDate, String emailId, String phone, int status) {
        super();
        this.userId = userId;
        this.name = name;
        this.joiningDate = joiningDate;
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

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
