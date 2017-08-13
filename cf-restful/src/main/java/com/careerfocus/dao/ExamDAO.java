package com.careerfocus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExamDAO {

	@Autowired
	private JdbcTemplate template;
	@Autowired
	private categoryDAO categoryDAO;
	@Autowired
	private CommonDAO commonDAO;
	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	public ExamDAO(JdbcTemplate template) {
		this.template = template;
	}

	public Map<String, Object> getExamCategoryDetails(int examId) {
		Map<String, Object> categoriesQStatusList = new HashMap<String, Object>();
		List<Map<String, Object>> categoryList = categoryDAO.getCategoriesOfAnExam(examId);
		for (Map<String, Object> m : categoryList) {
			int categoryId = (Integer) m.get("category_id");
			String categoryName = (String) m.get("name");
			categoriesQStatusList.put(categoryName, getCategorySpecificQStatusList(examId, categoryId));
		}
		return categoriesQStatusList;
	}

	public List<Map<String, Object>> getCategorySpecificQStatusList(int examId, int categoryId) {
		String query = "SELECT question_status_id, status, count, category_id FROM"
				+ " (SELECT question_status,COUNT(*) AS count, category_id FROM exam_question"
				+ " where exam_id = ? and category_id = ? GROUP BY question_status) q"
				+ " inner join question_status qs on qs.question_status_id = q.question_status";
		return template.queryForList(query, examId, categoryId);
	}

	public List<Map<String, Object>> getCategoriesQStatusList(int examId, int categoryId) {
		String query = "SELECT question_no,question_status FROM exam_question"
				+ " where exam_id = ? and category_id = ?";
		return template.queryForList(query, examId, categoryId);
	}

	public boolean startExam(int examId, int language) {
		String query = "UPDATE exam	SET	start_time = now(),end_time = now(),question_answered = 0,"
				+ "question_correct = 0,mark_correct = 0,mark_negative = 0,language = ? WHERE exam_id = ?";
		if (template.update(query, language, examId) > 0)
			return true;
		else
			return false;
	}

	// public boolean stopExam(int examId) {
	// String query = "UPDATE exam SET start_time = now(),end_time =
	// now(),question_answered = 0,"
	// + "question_correct = 0,mark_correct = 0,mark_negative = 0,language = ?
	// WHERE exam_id = ?";
	// if (template.update(query, examId) > 0)
	// return true;
	// else
	// return false;
	// }

	public int createExam(int testId, int isDemo) {
		int examId = 0;
		String query = "INSERT INTO exam(test_id,is_demo) VALUES (?,?)";
		examId = template.update(query, testId, isDemo);
		return examId;
	}

	public boolean saveTime(int examId, int categoryId) {
		boolean status = false;
		Date timeLastUpdated = getCategoryLastUpdateTime(examId, categoryId);
		long timeInSecs = commonDAO.getTimeDifferenceInSec(timeLastUpdated);
		String query = "UPDATE exam_category_time set total_time = ? where exam_id= ? and category_id = ?";
		status = template.update(query, timeInSecs, examId, categoryId) > 0 ? true : false;
		if (status)
			status = categoryDAO.updateCategoryLastUpdateTime(examId, categoryId);
		return status;
	}

	public List<Map<String, Object>> getNoOfQsPerCategory(int examId) {
		String query = "select category_id, qpc.no_of_questions from question_paper_category qpc"
				+ " inner join question_paper qp on qpc.question_paper_id = qp.question_paper_id"
				+ " inner join test t on t.question_paper_id= qp.question_paper_id where t.exam_id = ?";
		List<Map<String, Object>> results = template.queryForList(query, examId);
		return results;
	}

	public int createExamQuestions(int examId) {
		Map<String, Object> qIdDetails = questionDAO.getQuestionId(examId);
		int categoryId = (Integer) qIdDetails.get("category_id");
		int qId = (Integer) qIdDetails.get("no_of_questions");
		String query = "INSERT INTO exam_question (exam_id,question_id,question_no,"
				+ "question_status,is_correct,category_id) VALUES()";
		int examQId = template.update(query, examId, qId, 0, 0, categoryId);
		return examQId;
	}

	public boolean createExamCategoryTime(int examId, int categoryId) {
		boolean status = true;
		String query = "INSERT INTO exam_category_time (exam_id,category) VALUES (?,?)";
		status = template.update(query, examId, categoryId) > 0 ? true : false;
		return status;
	}

	public Date getCategoryLastUpdateTime(int examId, int categoryId) {
		String query = "select last_update_time from exam_category_time where exam_id = ?";
		Date time = template.queryForObject(query, Date.class, examId);
		return time;
	}

	public Integer getQuestionPaperIdFromExamId(int examId) {
		String query = "select bqp.question_paper_id from exam e inner join test t on e.test_id = t.test_id"
				+ " inner join bundle_question_paper bqp on bqp.bundle_question_paper_id = t.bundle_question_paper_id"
				+ " where e.exam_id = ?";
		return template.queryForObject(query, Integer.class, examId);
	}

}
