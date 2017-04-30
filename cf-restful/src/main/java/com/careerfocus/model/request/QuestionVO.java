package com.careerfocus.model.request;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionVO implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int questionPaperSubCategoryId;

	private int questionNo;
	
	private ArrayList<OptionVO> options;
	
	private String question;
	
	private int correctOptionNo;
	
	private int questionId;
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionPaperSubCategoryId() {
		return this.questionPaperSubCategoryId;
	}

	public void setQuestionPaperSubCategoryId(int questionPaperSubCategoryId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
	}

	public int getQuestionNo() {
		return this.questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<OptionVO> getOptions() {
		return this.options;
	}

	public void setOptions(ArrayList<OptionVO> options) {
		this.options = options;
	}

	public int getCorrectOptionNo() {
		return this.correctOptionNo;
	}

	public void setCorrectOptionNo(int correctOptionNo) {
		this.correctOptionNo = correctOptionNo;
	}

}
