package com.careerfocus.repository;

import com.careerfocus.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Integer> {

}
