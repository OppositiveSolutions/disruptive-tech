package com.careerfocus.repository;

import com.careerfocus.entity.QuestionPaperSubCategory;
import com.careerfocus.model.response.QPSubCategoryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionPaperSubCategoryRepository extends JpaRepository<QuestionPaperSubCategory, Integer> {

    @Query("SELECT new com.careerfocus.model.response.QPSubCategoryVO(q.questionPaperSubCategoryId, q.content, " +
            "q.description, q.noOfQuestions, q.isImage) FROM QuestionPaperSubCategory q WHERE q.questionPaperCategoryId=?1")
    List<QPSubCategoryVO> findByQuestionPaperCategoryId(int questionPaperSubCategoryId);

}
