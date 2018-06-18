package com.careerfocus.repository;

import com.careerfocus.entity.QuestionPaperQuestion;
import com.careerfocus.entity.id.QuestionPaperQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionPaperQuestionRepository extends JpaRepository<QuestionPaperQuestion, QuestionPaperQuestionId> {

    List<QuestionPaperQuestion> findByQuestionPaperSubCategoryId(int questionPaperSubCategoryId);

}
