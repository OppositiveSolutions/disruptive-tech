package com.careerfocus.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", columnDefinition = "INT")
    int questionId;

    @Basic
    @Column(nullable = false, length = 2000)
    String question;

    @Basic
    @Column(name = "correct_option_no", nullable = false, length = 2000)
    int correctOptionNo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @OrderBy("option_no")
    private Set<QuestionOption> options = new HashSet<QuestionOption>();

    public Question() {

    }

    public Question(int questionId, String question, int correctOptionNo) {
        this.questionId = questionId;
        this.question = question;
        this.correctOptionNo = correctOptionNo;
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

    public Set<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(Set<QuestionOption> options) {
        this.options = options;
    }

}
