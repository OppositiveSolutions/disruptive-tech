package com.careerfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.Question;

public interface QuestionsRepository extends JpaRepository<Question, Integer> {

}
