package com.careerfocus.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.careerfocus.entity.QuestionPaper;

@Repository
public class QuestionPaperDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<QuestionPaper> getAllQuestionPapers() {
		String query = "SELECT * FROM question_paper";
		return jdbcTemplate.query(query, new RowMapper<QuestionPaper>(){

			@Override
			public QuestionPaper mapRow(ResultSet result, int arg1) throws SQLException {
				QuestionPaper qPaper = new QuestionPaper();
				qPaper.setQuestionPaperId(result.getInt("question_paper_id"));
				qPaper.setName(result.getString("name"));
				qPaper.setExamCode(result.getString("exam_code"));
				qPaper.setCourseName(result.getString("course_name"));
				qPaper.setDuration(180);
				qPaper.setNoOfQuestions(result.getInt("no_of_questions"));
				qPaper.setNoOfOptions(result.getInt("no_of_options"));
				qPaper.setCorrectAnswerMark(result.getInt("correct_answer_mark"));
				qPaper.setNegativeMark(result.getFloat("negative_mark"));
				qPaper.setIsDemo(result.getBoolean("is_demo"));
				qPaper.setQuestionPaperCategorys(null);
				return qPaper;
			}
			
		});
		
	}
	
}
