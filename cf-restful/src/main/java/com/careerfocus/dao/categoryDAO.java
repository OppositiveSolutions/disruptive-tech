package com.careerfocus.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class categoryDAO {

	private JdbcTemplate template;

	@Autowired
	public categoryDAO(JdbcTemplate template) {
		this.template = template;
	}

	public String getCategoryNameFromCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateCategoryLastUpdateTime(int examId, int categoryId) {
		boolean status = false;
		String query = "UPDATE exam_category_time set last_update_time = now() where exam_id= ?";
		status = template.update(query, examId) > 0 ? true : false;
		return status;
	}

	public List<Map<String, Object>> getCategoriesOfAnExam(int examId) {
		String query = "select c.category_id,c.name,qpc.no_of_questions,qpc.correct_answer_mark,"
				+ " qpc.negative_mark,qpc.duration from category c"
				+ " inner join question_paper_category qpc on qpc.category_id = c.category_id"
				+ " inner join question_paper qp on qp.question_paper_id = qpc.question_paper_id"
				+ " inner join bundle_question_paper bqp on bqp.question_paper_id = qp.question_paper_id"
				+ " inner join test t on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
				+ " inner join exam e on e.test_id = t.test_id where e.exam_id = ?";
		return template.queryForList(query, examId);
	}

}
