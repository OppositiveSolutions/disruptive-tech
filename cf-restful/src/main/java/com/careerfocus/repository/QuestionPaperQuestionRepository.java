package com.careerfocus.repository;

import com.careerfocus.entity.QuestionPaperQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionPaperQuestionRepository extends JpaRepository<QuestionPaperQuestion, Integer> {

//    @Query("SELECT qpq FROM QuestionPaperQuestion qpq ve")
//    String findQuestionsByQuestionPaperSubCategoryId(int questionPaperSubCategoryId );

}
