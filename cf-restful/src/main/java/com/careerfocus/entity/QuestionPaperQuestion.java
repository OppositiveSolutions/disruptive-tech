package com.careerfocus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.careerfocus.entity.id.QuestionPaperQuestionId;

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

//	@Column(name = "question_id", nullable = false, columnDefinition = "INT")
//	private int questionId;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch= FetchType.EAGER)
	@JoinColumn(name = "question_id", columnDefinition="INT", nullable=false)
	private Question question;

	public QuestionPaperQuestion() {

	}

	public QuestionPaperQuestion(int questionPaperSubCategoryId, int questionNo, Question question) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
		this.questionNo = questionNo;
		this.question = question;
//		this.questionId = questionId;
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

//	public int getQuestionId() {
//		return questionId;
//	}
//
//	public void setQuestionId(int questionId) {
//		this.questionId = questionId;
//	}
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
