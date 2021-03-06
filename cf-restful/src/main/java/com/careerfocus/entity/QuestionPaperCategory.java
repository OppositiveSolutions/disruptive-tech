package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "question_paper_category")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionPaperCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_paper_category_id", columnDefinition = "INT")
    private int questionPaperCategoryId;

//	@JsonManagedReference
//	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
//	@JoinColumn(name="question_paper_id", columnDefinition="INT", nullable=false)
//	private int questionPaper;

    @Column(name = "question_paper_id")
    private int questionPaperId;

    public int getQuestionPaperId() {
        return questionPaperId;
    }

    public void setQuestionPaperId(int questionPaperId) {
        this.questionPaperId = questionPaperId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", columnDefinition = "INT", nullable = false)
    private Category category;

    @Basic
    @Column(name = "no_of_questions", columnDefinition = "INT")
    private int noOfQuestions;

    @Basic
    @Column(name = "no_of_sub_category", columnDefinition = "INT")
    private int noOfSubCategory;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_paper_category_id")
    @OrderBy("question_paper_sub_category_id")
    private Set<QuestionPaperSubCategory> questionPaperSubCategorys = new HashSet<>();

    @Basic
    @Column(name = "correct_answer_mark", columnDefinition = "INT")
    private float correctAnswerMark;

    @Basic
    @Column(name = "negative_mark", columnDefinition = "FLOAT")
    private float negativeMark;

    @Basic
    @Column(name = "r_order", columnDefinition = "INT")
    private int order;

    @Basic
    @Column(name = "duration", columnDefinition = "INT")
    private int duration;

    public QuestionPaperCategory() {
    }

    public QuestionPaperCategory(int questionPaperCategoryId) {
        this.questionPaperCategoryId = questionPaperCategoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public int getNoOfSubCategory() {
        return noOfSubCategory;
    }

    public void setNoOfSubCategory(int noOfSubCategory) {
        this.noOfSubCategory = noOfSubCategory;
    }

    public int getQuestionPaperCategoryId() {
        return questionPaperCategoryId;
    }

    public void setQuestionPaperCategoryId(int questionPaperCategoryId) {
        this.questionPaperCategoryId = questionPaperCategoryId;
    }

    public Set<QuestionPaperSubCategory> getQuestionPaperSubCategorys() {
        return questionPaperSubCategorys;
    }

    public void setQuestionPaperSubCategorys(Set<QuestionPaperSubCategory> questionPaperSubCategorys) {
        this.questionPaperSubCategorys = questionPaperSubCategorys;
    }

    public float getCorrectAnswerMark() {
        return correctAnswerMark;
    }

    public void setCorrectAnswerMark(float correctAnswerMark) {
        this.correctAnswerMark = correctAnswerMark;
    }

    public float getNegativeMark() {
        return negativeMark;
    }

    public void setNegativeMark(float negativeMark) {
        this.negativeMark = negativeMark;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}