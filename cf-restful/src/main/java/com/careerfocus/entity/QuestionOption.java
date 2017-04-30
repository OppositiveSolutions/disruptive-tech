package com.careerfocus.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "question_option")
public class QuestionOption {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "question_option_id", columnDefinition = "INT")
//	private int questionOptionId;

	@Basic
	@Column(name = "question_id", columnDefinition = "INT")
	private int questionId;

	@Basic
	@Column(name = "option_no", nullable = false, columnDefinition = "INT")
	private int optionNo;

	@Basic
	@Column(nullable = false, length = 1000)
	private String option;

	public QuestionOption() {

	}
	
	public QuestionOption(int questionId, int optionNo, String option) {
		this.questionId = questionId;
		this.optionNo = optionNo;
		this.option = option;
	}

//	public int getQuestionOptionId() {
//		return questionOptionId;
//	}
//
//	public void setQuestionOptionId(int questionOptionId) {
//		this.questionOptionId = questionOptionId;
//	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getOptionNo() {
		return this.optionNo;
	}

	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}

	public String getOption() {
		return this.option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
