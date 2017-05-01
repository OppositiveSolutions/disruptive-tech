package com.careerfocus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
		return jdbcTemplate.query(query, new RowMapper<QuestionPaper>() {

			@Override
			public QuestionPaper mapRow(ResultSet result, int arg1) throws SQLException {
				QuestionPaper qPaper = new QuestionPaper();
				qPaper.setQuestionPaperId(result.getInt("question_paper_id"));
				qPaper.setName(result.getString("name"));
				qPaper.setExamCode(result.getString("exam_code"));
				qPaper.setCourseName(result.getString("course_name"));
				qPaper.setDuration(result.getInt("duration"));
				qPaper.setNoOfQuestions(result.getInt("no_of_questions"));
				qPaper.setNoOfOptions(result.getInt("no_of_options"));
				qPaper.setCreatedDate(result.getTimestamp("created_date"));
				qPaper.setLastModified(result.getTimestamp("last_modified"));
				qPaper.setIsDemo(result.getBoolean("is_demo"));
				qPaper.setQuestionPaperCategorys(null);
				return qPaper;
			}

		});
	}

	public void updateLastModified(List<Integer> qIds) {

		String query = "UPDATE question_paper SET last_modified = current_timestamp() WHERE question_paper_id=?";

		jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, qIds.get(0));
			}

			@Override
			public int getBatchSize() {
				return qIds.size();
			}
		});
	}

	public void updateLastModidiedByQuestionPaperSubCategoryIds(List<Integer> ids) {
		if (ids.isEmpty())
			return;

		String query = "UPDATE question_paper qp INNER JOIN question_paper_category qpc ON qp.question_paper_id=qpc.question_paper_id\n"
				+ "INNER JOIN question_paper_sub_category qpsc ON qpc.question_paper_category_id=qpsc.question_paper_category_id\n"
				+ "SET qp.last_modified = current_timestamp() WHERE qpsc.question_paper_sub_category_id IN (";
		for (int id : ids)
			query += id + ", ";
		query = query.substring(0, query.length() - 2) + ")";
		
		jdbcTemplate.update(query);
	}

}
