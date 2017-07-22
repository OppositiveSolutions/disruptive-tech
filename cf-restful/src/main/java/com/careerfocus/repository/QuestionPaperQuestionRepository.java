package com.careerfocus.repository;

import com.careerfocus.entity.QuestionPaperQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionPaperQuestionRepository extends JpaRepository<QuestionPaperQuestion, Integer> {

    List<QuestionPaperQuestion> findByQuestionPaperSubCategoryId(int questionPaperSubCategoryId);

}
