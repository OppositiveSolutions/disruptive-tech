package com.careerfocus.entity.id;

import java.io.Serializable;

public class QuestionPaperQuestionId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int questionPaperSubCategoryId;

    private int questionNo;

    public QuestionPaperQuestionId() {

    }

    public QuestionPaperQuestionId(int questionPaperSubCategoryId, int questionNo) {
        this.questionPaperSubCategoryId = questionPaperSubCategoryId;
        this.questionNo = questionNo;
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


}
