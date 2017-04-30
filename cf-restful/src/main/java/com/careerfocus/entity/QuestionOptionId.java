package com.careerfocus.entity;

import java.io.Serializable;

public class QuestionOptionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int questionId;
	
	private int optionNo;
	
	public QuestionOptionId() {
		
	}
	
	public QuestionOptionId (int questionId, int optionNo) {
		this.questionId = questionId;
		this.optionNo = optionNo;
	}
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getOptionNo() {
		return optionNo;
	}

	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}

}
