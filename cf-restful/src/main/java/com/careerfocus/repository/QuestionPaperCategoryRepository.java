package com.careerfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.QuestionPaperCategory;

public interface QuestionPaperCategoryRepository extends JpaRepository<QuestionPaperCategory, Integer> {

	public List<QuestionPaperCategory> findByQuestionPaperId(int questionPaperId);
}
