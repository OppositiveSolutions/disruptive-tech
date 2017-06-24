package com.careerfocus.repository;

import com.careerfocus.entity.QuestionPaperCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionPaperCategoryRepository extends JpaRepository<QuestionPaperCategory, Integer> {

    List<QuestionPaperCategory> findByQuestionPaperId(int questionPaperId);

    List<QuestionPaperCategory> findByQuestionPaperCategoryIdIn(List<Integer> inventoryIdList);
}
