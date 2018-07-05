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
@Table(name = "question_paper_sub_category_image")
public class QuestionPaperSubCategoryImage {

	@Id
	@Column(name = "question_paper_sub_category_id", columnDefinition = "INT")
	private int questionPaperSubCategoryId;

	@Basic
	@Column(columnDefinition = "BLOB", nullable = false)
	private byte[] image;

	@JsonIgnore
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "questionPaperSubCategoryId", scope = Question.class)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "question_paper_sub_category_id", columnDefinition = "INT", nullable = false, insertable = false, updatable = false)
	private QuestionPaperSubCategory questionPaperSubCategory;

	public QuestionPaperSubCategoryImage() {
	}

	public QuestionPaperSubCategoryImage(int questionPaperSubCategoryId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
	}

	public QuestionPaperSubCategoryImage(int questionPaperSubCategoryId, byte[] image) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
		this.image = image;
	}

	public int getQuestionPaperSubCategoryId() {
		return questionPaperSubCategoryId;
	}

	public void setQuestionPaperSubCategoryId(int questionPaperSubCategoryId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public QuestionPaperSubCategory getQuestionPaperSubCategory() {
		return questionPaperSubCategory;
	}

	public void setQuestionPaperSubCategory(QuestionPaperSubCategory questionPaperSubCategory) {
		this.questionPaperSubCategory = questionPaperSubCategory;
	}
}