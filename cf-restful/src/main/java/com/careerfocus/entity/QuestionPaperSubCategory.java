package com.careerfocus.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "question_paper_sub_category")
public class QuestionPaperSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_paper_sub_category_id", columnDefinition = "INT")
	private int questionPaperSubCategoryId;

	@Column(name = "question_paper_category_id", columnDefinition = "INT")
	private int questionPaperCategoryId;

	@Basic
	private String content;

	@Basic
	private String description;

	@Basic
	@Column(name = "no_of_questions", columnDefinition = "INT")
	private int noOfQuestions;

	@Basic
	@Column(name = "r_order", columnDefinition = "INT")
	private int order;

	@Basic
	@Column(name = "is_image")
	private String isImage;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "question_paper_sub_category_id")
	@OrderBy("questionNo")
	private Set<QuestionPaperQuestion> questions = new HashSet<QuestionPaperQuestion>();

	@OneToMany(targetEntity = QuestionPaperSubCategoryImage.class, mappedBy = "questionPaperSubCategory", cascade = CascadeType.ALL)
	private Set<QuestionPaperSubCategoryImage> questionPaperSubCategoryImage = new HashSet<QuestionPaperSubCategoryImage>();

	// @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	// @JoinColumn(direction="question_paper_category_id",
	// columnDefinition="INT", nullable=false)
	// private QuestionPaperCategory questionPaperCategory;

	public QuestionPaperSubCategory() {
	}

	public QuestionPaperSubCategory(int questionPaperSubCategoryId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
	}

	public int getQuestionPaperCategoryId() {
		return questionPaperCategoryId;
	}

	public void setQuestionPaperCategoryId(int questionPaperCategoryId) {
		this.questionPaperCategoryId = questionPaperCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	// public QuestionPaperCategory getQuestionPaperCategory() {
	// return questionPaperCategory;
	// }
	//
	// public void setQuestionPaperCategory(QuestionPaperCategory
	// questionPaperCategory) {
	// this.questionPaperCategory = questionPaperCategory;
	// }

	public int getQuestionPaperSubCategoryId() {
		return questionPaperSubCategoryId;
	}

	public void setQuestionPaperSubCategoryId(int questionPaperSubCategoryId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getIsImage() {
		return isImage;
	}

	public void setIsImage(String isImage) {
		this.isImage = isImage;
	}

	public Set<QuestionPaperQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionPaperQuestion> questions) {
		this.questions = questions;
	}

	public Set<QuestionPaperSubCategoryImage> getQuestionPaperSubCategoryImage() {
		return questionPaperSubCategoryImage;
	}

	public void setQuestionPaperSubCategoryImage(Set<QuestionPaperSubCategoryImage> questionPaperSubCategoryImage) {
		this.questionPaperSubCategoryImage = questionPaperSubCategoryImage;
	}

}