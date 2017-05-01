package com.careerfocus.entity;

import java.util.*;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "question_paper")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionPaper {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_paper_id", columnDefinition = "INT")
	private int questionPaperId;

	@Basic
	@Column(nullable = false, length = 150)
	private String name;

	@Basic
	@Column(name = "exam_code", nullable = false, length = 45)
	private String examCode;

	@Basic
	@Column(name = "course_name", nullable = false)
	private String courseName;

	@Basic
	@Column(columnDefinition = "INT")
	private int duration;

	@Basic
	@Column(name = "no_of_questions", columnDefinition = "INT")
	private int noOfQuestions;

	@Basic
	@Column(name = "no_of_options", columnDefinition = "INT")
	private int noOfOptions;

	@Basic
	@Column(name = "created_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Basic
	@Column(name = "last_modified", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@Basic
	@Column(name = "is_demo")
	private boolean isDemo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "question_paper_id")
	@OrderBy("order")
	private Set<QuestionPaperCategory> questionPaperCategorys = new HashSet<QuestionPaperCategory>();

	public QuestionPaper() {
	}

	public QuestionPaper(int questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public boolean isIsDemo() {
		return isDemo;
	}

	public void setIsDemo(boolean isDemo) {
		this.isDemo = isDemo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfOptions() {
		return noOfOptions;
	}

	public void setNoOfOptions(int noOfOptions) {
		this.noOfOptions = noOfOptions;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public Set<QuestionPaperCategory> getQuestionPaperCategorys() {
		return questionPaperCategorys;
	}

	public void setQuestionPaperCategorys(Set<QuestionPaperCategory> questionPaperCategorys) {
		this.questionPaperCategorys = questionPaperCategorys;
	}

	public int getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(int questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	// public boolean isDemo() {
	// return isDemo;
	// }
	//
	// public void setDemo(boolean isDemo) {
	// this.isDemo = isDemo;
	// }

}