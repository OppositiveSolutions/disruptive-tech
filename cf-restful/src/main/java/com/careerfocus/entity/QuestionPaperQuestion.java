package com.careerfocus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "question_paper_question")
public class QuestionPaperQuestion {

	@Id
	@Column(name = "question_id", columnDefinition = "INT", nullable = false)
	private int questionId;

	@Id
	@Column(name = "option_no", nullable = false, columnDefinition = "INT")
	private int optionNo;

	
}
