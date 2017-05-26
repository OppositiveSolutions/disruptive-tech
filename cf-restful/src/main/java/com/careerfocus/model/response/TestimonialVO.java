package com.careerfocus.model.response;

import java.util.Date;

import com.careerfocus.constants.Constants;

public class TestimonialVO {
	
	private int testimonialId;
	
	private String content;
	
	private Date date;
	
	private String name;
	
	private int userId;
	
	private String profilePicUrl;
	
	
	public TestimonialVO() {
		
	}
	
	public TestimonialVO(int testimonialId, String content, Date date, String name, int userId) {
		super();
		this.testimonialId = testimonialId;
		this.content = content;
		this.date = date;
		this.name = name;
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
