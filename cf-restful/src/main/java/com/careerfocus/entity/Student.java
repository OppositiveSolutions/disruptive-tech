package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "student")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Student {

	@Id
	@Column(name = "user_id", columnDefinition = "INT")
	private int userId;

	@Basic
	@Column(name = "expiry_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@Basic
	@Column(name = "fee_status", nullable = false, length = 45)
	private String feeStatus;

	@Basic
	private String qualification;

	@Column(name = "center_id", insertable = false, updatable = false)
	private int centerId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "center_id", columnDefinition = "INT", nullable = false)
	private Center center;

	@Basic
	@Column(columnDefinition = "INT")
	private int status;

	@Basic
	@Column(columnDefinition = "INT")
	private int type;

	@JsonManagedReference
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", scope = User.class)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", columnDefinition = "INT")
	private User user;

	public Student() {
	}

	public Student(int userId) {
		this.userId = userId;
	}

	public Student(int userId, String qualification, int status, String centerId, String feeStatus, Date expiryDate,
			Center center, int type) {
		this.userId = userId;
		this.qualification = qualification;
		this.status = status;
		this.feeStatus = feeStatus;
		this.expiryDate = expiryDate;
		this.center = center;
		this.type = type;
	}

	// public String getCenterId() {
	// return centerId;
	// }
	//
	// public void setCenterId(String centerId) {
	// this.centerId = centerId;
	// }

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

}