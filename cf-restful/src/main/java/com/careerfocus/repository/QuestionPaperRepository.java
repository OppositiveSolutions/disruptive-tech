package com.careerfocus.repository;

import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.model.response.QuestionPaperVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Integer> {

    @Modifying
    @Query("UPDATE QuestionPaper q SET q.lastModified = CURRENT_TIMESTAMP WHERE q.questionPaperId = ?1")
    void updateTimeStampByQuestionPaperId(int questionPaperId);

    @Modifying
    @Query("UPDATE QuestionPaper q SET q.isDemo = ?1 WHERE q.questionPaperId = ?2")
    void updateIsDemo(boolean isDemo, int questionPaperId);

    @Query("SELECT new com.careerfocus.model.response.QuestionPaperVO(q) FROM QuestionPaper q")
    Page<QuestionPaperVO> findAllQuestionsPapers(Pageable page);

    @Query("SELECT new com.careerfocus.model.response.QuestionPaperVO(q) FROM QuestionPaper q "
            + "WHERE LOWER(q.examCode) LIKE LOWER(:key) OR LOWER(q.courseName) LIKE LOWER(:key) OR LOWER(q.name) LIKE LOWER(:key)")
    Page<QuestionPaperVO> searchQuestionsPaper(@Param("key") String key, Pageable page);

}
