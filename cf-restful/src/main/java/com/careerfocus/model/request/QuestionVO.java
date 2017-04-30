package com.careerfocus.model.request;

import java.util.ArrayList;

public class QuestionVO {

	private int questionPaperSubCategoryId;

	private int questionNo;
	
	private ArrayList<Option> options;
	
	private String question;
	
	private int correctOptionNo;
	
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

	public ArrayList<Option> getOptions() {
		return this.options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public int getCorrectOptionNo() {
		return this.correctOptionNo;
	}

	public void setCorrectOptionNo(int correctOptionNo) {
		this.correctOptionNo = correctOptionNo;
	}

	public class Option {
		private int optionNo;

		public int getOptionNo() {
			return this.optionNo;
		}

		public void setOptionNo(int optionNo) {
			this.optionNo = optionNo;
		}

		private String option;

		public String getOption() {
			return this.option;
		}

		public void setOption(String option) {
			this.option = option;
		}
	}
}
