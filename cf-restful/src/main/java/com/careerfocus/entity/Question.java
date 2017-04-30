package com.careerfocus.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id", columnDefinition="INT")
	int questionId;
	
	@Basic
	@Column(nullable=false, length=2000)
	String question;
	
	@Basic
	@Column(name = "correct_option_no", nullable=false, length=2000)
	int correctOptionNo;
	
	public Question() {
		
	}
	
	public Question(String question, int correctOptionNo) {
		this.question = question;
		this.correctOptionNo = correctOptionNo;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getCorrectOptionNo() {
		return correctOptionNo;
	}

	public void setCorrectOptionNo(int correctOptionNo) {
		this.correctOptionNo = correctOptionNo;
	}
	
}
