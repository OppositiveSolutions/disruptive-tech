package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "question_image")
public class QuestionImage {

	@Id
	@Column(name = "question_id", columnDefinition = "INT")
	private int questionId;

	@Basic
	@Column(name = "description")
	private String description;

	@Basic
	@Column(columnDefinition = "BLOB", nullable = false)
	private byte[] image;

	@JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "questionId", scope = Question.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", columnDefinition = "INT", nullable = false, insertable = false, updatable = false)
    private Question question;

	public QuestionImage() {
	}

	public QuestionImage(int questionId) {
		this.questionId = questionId;
	}

	public QuestionImage(int questionId, byte[] image, String description) {
		this.questionId = questionId;
		this.image = image;
		this.description = description;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}