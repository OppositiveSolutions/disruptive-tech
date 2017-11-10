package com.careerfocus.dao;

import com.careerfocus.model.request.SaveQuestionVO;
import com.careerfocus.model.response.QuestionPopulateVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class QuestionDAO {

	private JdbcTemplate template;

	@Autowired
	public QuestionDAO(JdbcTemplate template) {
		this.template = template;
	}

	public QuestionPopulateVO getQuestion(int examId, int qNo) {
		return null;
	}

	public boolean saveQuestion(SaveQuestionVO question, int examId) {
		String query = "INSERT INTO exam_question(exam_id,question_id,"
				+ "question_no,option_entered,question_status,category_id) VALUES (?,?,?,?,?,?)";
		if (template.update(query, examId, question.getQuestionId(), question.getQuestionNo(), question.getOptionNo(),
				question.getStatus(), question.getCategoryId()) > 0)
			return true;
		else
			return false;
	}

	public boolean clearExamQuestion(int examId) {
		String query = "DELETE FROM exam_question WHERE exam_id = ? and exam_question_id > 0";
		if (template.update(query, examId) > 0)
			return true;
		else
			return false;
	}

	public Map<String, Object> getQuestionIds(int examId) {
		// TODO Auto-generated method stub
		String query = "select category_id, question_id, question_no from question_paper_question qpq"
				+ " inner join question_paper_sub_category qpsc on qpq.question_paper_sub_category_id=qpsc.question_paper_sub_category_id"
				+ " inner join question_paper_category qpc on qpc.question_paper_category_id = qpsc.question_paper_category_id"
				+ " inner join question_paper qp on qpc.question_paper_id = qp.question_paper_id"
				+ " inner join test t on t.question_paper_id= qp.question_paper_id"
				+ " inner join exam e on t.test_id = e.test_id where e.exam_id = ?";
		Map<String, Object> result = template.queryForMap(query, examId);
		return result;
	}

}
