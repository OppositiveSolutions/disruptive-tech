package com.careerfocus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "question_paper_question")
@IdClass(QuestionPaperQuestionId.class)
public class QuestionPaperQuestion {

	@Id
	@Column(name = "question_paper_sub_category_id", columnDefinition = "INT", nullable = false)
	private int questionPaperSubCategoryId;

	@Id
	@Column(name = "question_no", nullable = false, columnDefinition = "INT")
	private int questionNo;

	@Column(name = "question_id", nullable = false, columnDefinition = "INT")
	private int questionId;

	public QuestionPaperQuestion() {

	}

	public QuestionPaperQuestion(int questionPaperSubCategoryId, int questionNo, int questionId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
		this.questionNo = questionNo;
		this.questionId = questionId;
	}

	public int getQuestionPaperSubCategoryId() {
		return questionPaperSubCategoryId;
	}

	public void setQuestionPaperSubCategoryId(int questionPaperSubCategoryId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}
