package com.careerfocus.model.response;

import com.careerfocus.constants.Constants;

import java.util.Date;

public class TestimonialVO {

	private int testimonialId;

	private String content;

	private String contact;

	private String description;

	private String name;

	private Date date;

	private String userName;

	private int userId;

	private String profilePicUrl;

	public TestimonialVO() {

	}

	public TestimonialVO(int testimonialId, String content, String contact, String description, String name, Date date,
			String userName, int userId) {
		super();
		this.testimonialId = testimonialId;
		this.content = content;
		this.name = contact;
		this.name = description;
		this.name = name;
		this.date = date;
		this.name = userName;
		this.userId = userId;
		this.profilePicUrl = Constants.RESTFUL_PATH_PREFIX;
	}

	public int getTestimonialId() {
		return testimonialId;
	}

	public void setTestimonialId(int testimonialId) {
		this.testimonialId = testimonialId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

}
