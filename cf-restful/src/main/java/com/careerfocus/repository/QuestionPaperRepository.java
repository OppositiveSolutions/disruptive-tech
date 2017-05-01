package com.careerfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.careerfocus.entity.QuestionPaper;

public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Integer>{

	@Modifying
	@Query("UPDATE QuestionPaper q SET q.lastModified = CURRENT_TIMESTAMP WHERE q.questionPaperId = ?1")
	void updateTimeStampByQuestionPaperId(int questionPaperId);
	
	@Modifying
	@Query("UPDATE QuestionPaper q SET q.isDemo = ?1 WHERE q.questionPaperId = ?2")
	void updateIsDemo(boolean isDemo, int questionPaperId);
	
}
