package com.careerfocus.repository;

import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.QuestionPaperSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionPaperSubCategoryRepository extends JpaRepository<QuestionPaperSubCategory, Integer> {

    List<QuestionPaperSubCategory> findByQuestionPaperCategoryId(int questionPaperSubCategoryId);


}
