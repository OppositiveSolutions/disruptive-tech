package com.careerfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.QuestionOption;

public interface QuestionOptionsRepository extends JpaRepository<QuestionOption, Integer> {

//	public List<QuestionOption> findByQuestionId(int questionId);
}
