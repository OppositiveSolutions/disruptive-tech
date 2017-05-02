package com.careerfocus.entity;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name="student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {
	@Id
	@Column(name="student_id", columnDefinition="INT")
	private int studentId;
	
	@Basic
	@Column(name="center_id", nullable=false, length=45)
	private String centerId;

	@Basic
	@Column(name="expiry_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@Basic
	@Column(name="fee_status", nullable=false, length=45)
	private String feeStatus;

	@Basic
	private String qualification;

	@Basic
	@Column(columnDefinition="INT")
	private int status;

	@JsonManagedReference
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId", scope=User.class)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id", columnDefinition="INT", nullable=false)
	private User user;

	public Student() {
	}

	public Student(int studentId) {
		this.studentId = studentId;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFeeStatus() {
		return feeStatus;
	}

	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}