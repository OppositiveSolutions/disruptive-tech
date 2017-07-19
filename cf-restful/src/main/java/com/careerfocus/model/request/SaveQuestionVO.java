package com.careerfocus.model.request;

import java.io.Serializable;

public class SaveQuestionVO implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int examId;

    private int questionNo;

    private int optionNo;
    
    private int status;

    private int questionId;

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public int getOptionNo() {
		return optionNo;
	}

	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
