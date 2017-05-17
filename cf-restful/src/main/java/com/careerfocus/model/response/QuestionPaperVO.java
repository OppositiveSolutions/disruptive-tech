package com.careerfocus.model.response;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.QuestionPaperCategory;
import com.careerfocus.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

public class QuestionPaperVO {

	private int questionPaperId;

	private String name;

	private String examCode;

	private String courseName;

	private int duration;

	private int noOfQuestions;

	private int noOfOptions;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_TIME_FORMAT, timezone=DateUtils.PRIMARY_TIMEZONE)
	private Date createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.PRIMARY_DATE_TIME_FORMAT, timezone=DateUtils.PRIMARY_TIMEZONE)
	private Date lastModified;

	private boolean isDemo;

	public QuestionPaperVO() {
	}
	
	public QuestionPaperVO(QuestionPaper qPaper) {
		questionPaperId = qPaper.getQuestionPaperId();
		name = qPaper.getName();
		examCode = qPaper.getExamCode();
		courseName = qPaper.getCourseName();
		duration = qPaper.getDuration();
		noOfQuestions = qPaper.getNoOfQuestions();
		noOfOptions = qPaper.getNoOfOptions();
		createdDate = qPaper.getCreatedDate();
		lastModified = qPaper.getLastModified();
		isDemo = qPaper.isIsDemo();
	}

	public QuestionPaperVO(int questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public boolean isIsDemo() {
		return isDemo;
	}

	public void setIsDemo(boolean isDemo) {
		this.isDemo = isDemo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfOptions() {
		return noOfOptions;
	}

	public void setNoOfOptions(int noOfOptions) {
		this.noOfOptions = noOfOptions;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public int getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(int questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
}
