package com.careerfocus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

	public boolean initializeExam(int examId, int language) {
		String query = "UPDATE exam	SET	start_time = now(), end_time = now(), question_answered = 0,"
				+ " question_correct_count = 0, question_wrong_count = 0, mark_correct = 0,"
				+ " mark_negative = 0, total_mark = 0, language = ? WHERE exam_id = ?";
		if (template.update(query, language, examId) > 0)
			return true;
		else
			return false;
	}

	public boolean initializeExamCategoryTime(int examId) {
		String query = "UPDATE exam_category_time	SET	last_update_time = now(), total_time = 0 WHERE exam_id = ?";
		if (template.update(query, examId) > 0)
			return true;
		else
			return false;
	}

	public int createExam(int testId, int isDemo) {
		String query = "INSERT INTO exam(test_id,is_demo) VALUES (?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, testId);
			ps.setInt(2, isDemo);
			return ps;
		}, holder);
		return holder.getKey().intValue();
	}

	public boolean saveTime(int examId, int categoryId) {
		boolean status = false;
		long totalTime = 0;
		int lastCategoryId = getLastVisitedCategoryId(examId, categoryId);
		Date timeLastUpdated = getCategoryLastUpdateTime(examId, lastCategoryId);
		long timeInSecs = commonDAO.getTimeDifferenceInSec(timeLastUpdated);
		totalTime = getCurrentTimeTakenPerCategory(examId, lastCategoryId) + timeInSecs;
		String query = "UPDATE exam_category_time set total_time = ? where exam_id= ? and category_id = ?";
		status = template.update(query, totalTime, examId, lastCategoryId) > 0 ? true : false;
		if (status) {
			status = categoryDAO.updateCategoryLastUpdateTime(examId, categoryId);
			status = categoryDAO.updateCategoryLastUpdateTime(examId, lastCategoryId);
		}
		return status;
	}

	public long getCurrentTimeTakenPerCategory(int examId, int categoryId) {
		long timeInSecs = 0;
		String query = "SELECT total_time from exam_category_time where exam_id = ? and category_id = ?";
		timeInSecs = template.queryForObject(query, Long.class, examId, categoryId);
		return timeInSecs;
	}

	public int getLastVisitedCategoryId(int examId, int categoryId) {
		int lastCategoryId = 0;
		String query = "SELECT last_category_id from exam where exam_id = ?";
		lastCategoryId = template.queryForObject(query, Integer.class, examId);
		query = "UPDATE exam set last_category_id = ? where exam_id= ?";
		template.update(query, categoryId, examId);
		return lastCategoryId;
	}

	public List<Map<String, Object>> getNoOfQsPerCategory(int examId) {
		String query = "select category_id, qpc.no_of_questions from question_paper_category qpc"
				+ " inner join question_paper qp on qpc.question_paper_id = qp.question_paper_id"
				+ " inner join bundle_question_paper bqp on bqp.question_paper_id = qp.question_paper_id"
				+ " inner join test t on t.bundle_question_paper_id= bqp.bundle_question_paper_id"
				+ " inner join exam e on e.test_id = t.test_id where e.exam_id = ?";
		List<Map<String, Object>> results = template.queryForList(query, examId);
		return results;
	}

	public boolean createExamCategoryTime(int examId, int categoryId) {
		boolean status = true;
		String query = "INSERT INTO exam_category_time (exam_id,category_id) VALUES (?,?)";
		status = template.update(query, examId, categoryId) > 0 ? true : false;
		return status;
	}

	public boolean createExamCategoryMark(int examId, int categoryId) {
		boolean status = true;
		String query = "INSERT INTO exam_category_mark (exam_id, category_id, no_of_correct_questions,"
				+ " no_of_wrong_questions, total_attended, correct_mark, negative_mark, total_mark)"
				+ " VALUES (?,?,0,0,0,0,0,0)";
		status = template.update(query, examId, categoryId) > 0 ? true : false;
		return status;
	}

	public Date getCategoryLastUpdateTime(int examId, int categoryId) {
		String query = "select last_update_time from exam_category_time where exam_id = ?";
		List<Date> time = template.queryForList(query, Date.class, examId);
		return time.get(0);
	}

	public Float getCategoryMark(int examId, int categoryId) {
		String query = "select total_mark from exam_category_mark where exam_id = ?";
		Float totalMark = template.queryForObject(query, Float.class, examId);
		return totalMark;
	}

	public Integer getQuestionPaperIdFromExamId(int examId) {
		String query = "select bqp.question_paper_id from exam e inner join test t on e.test_id = t.test_id"
				+ " inner join bundle_question_paper bqp on bqp.bundle_question_paper_id = t.bundle_question_paper_id"
				+ " where e.exam_id = ?";
		return template.queryForObject(query, Integer.class, examId);
	}

	public List<Map<String, Object>> getTotalTimeTakenPerCategory(int examId) {
		List<Map<String, Object>> categoryTimeList = null;
		String query = "SELECT c.name, ect.total_time from exam_category_time ect"
				+ " inner join category c on ect.category_id = c.category_id where ect.exam_id = ?";
		categoryTimeList = template.queryForList(query, examId);
		return categoryTimeList;
	}

	public boolean updateCategoryMark(int examId) {
		boolean status = false;
		double totalMark = 0.0;
		double correctMark = 0;
		double negativeMark = 0;
		double correctMarkPerQ = 0;
		double negativeMarkPerQ = 0;
		int totalAttended = 0;
		int totalCorrect = 0;
		int totalWrong = 0;
		String query = "select distinct category_id from question_paper_category qpc"
				+ " inner join bundle_question_paper bqp on qpc.question_paper_id = bqp.question_paper_id"
				+ " inner join test t on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
				+ " inner join exam e on e.test_id = t.test_id where e.exam_id = ?";
		List<Integer> categories = template.queryForList(query, Integer.class, examId);
		for (int c : categories) {
			System.out.println("Cate = " + c);
			query = "SELECT correct_answer_mark FROM question_paper_category qpc"
					+ " inner join bundle_question_paper bqp on bqp.question_paper_id = qpc.question_paper_id"
					+ " inner join test t on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
					+ " inner join exam e on t.test_id = e.test_id where category_id = ? and e.exam_id = ?";
			correctMarkPerQ = template.queryForObject(query, Double.class, c, examId);
			query = "SELECT negative_mark FROM question_paper_category qpc"
					+ " inner join bundle_question_paper bqp on bqp.question_paper_id = qpc.question_paper_id"
					+ " inner join test t on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
					+ " inner join exam e on t.test_id = e.test_id where category_id = ? and e.exam_id = ?";
			negativeMarkPerQ = template.queryForObject(query, Double.class, c, examId);
			query = "SELECT count(exam_question_id) FROM exam_question where question_status = 1 and category_id = ? and exam_id = ?";
			totalAttended = template.queryForObject(query, Integer.class, c, examId);
			query = "SELECT count(exam_question_id) FROM exam_question where question_status = 1 and category_id = ? and exam_id = ? and is_correct = 1";
			totalCorrect = template.queryForObject(query, Integer.class, c, examId);
			System.out.println("Mark = " + correctMarkPerQ + " - " + negativeMarkPerQ);
			if (totalCorrect > 0)
				correctMark = correctMarkPerQ * totalCorrect;
			else
				correctMark = 0;
			query = "SELECT count(exam_question_id) FROM exam_question where question_status = 1 and category_id = ? and exam_id = ? and is_correct = 0";
			totalWrong = template.queryForObject(query, Integer.class, c, examId);
			if (totalWrong > 0)
				negativeMark = negativeMarkPerQ * totalWrong;
			else
				negativeMark = 0;
			totalMark = correctMark - negativeMark;
			System.out.println(c + " - " + examId + " - " + totalMark + " - " + correctMark + " - " + negativeMark
					+ " - " + totalAttended + " - " + totalCorrect + " - " + totalWrong);
			status = saveCategoryMark(c, examId, totalMark, correctMark, negativeMark, totalAttended, totalCorrect,
					totalWrong);
			System.out.println("status = " + status);
			if (!status)
				break;
		}
		return status;
	}

	private boolean saveCategoryMark(int categoryId, int examId, double totalMark, double correctMark,
			double negativeMark, int totalAttended, int totalCorrect, int totalWrong) {
		boolean status = true;
		String query = "UPDATE exam_category_mark SET no_of_correct_questions = ?,"
				+ " no_of_wrong_questions = ?, total_attended = ?, correct_mark = ?,"
				+ " negative_mark = ?, total_mark = ? WHERE exam_id = ? AND category_id = ?";
		status = template.update(query, totalCorrect, totalWrong, totalAttended, correctMark, negativeMark, totalMark,
				examId, categoryId) > 0 ? true : false;
		System.out.println("s = " + status);
		return status;
	}

	public boolean updateExam(int id, int userId) {
		// id = examId or QpId
		boolean status = false;
		float correctOption = 0;
		float isUpdated = 0;
		String query = "SELECT * FROM exam_question where exam_id = ?";
		List<Map<String, Object>> examQs = template.queryForList(query, id);
		for (Map<String, Object> q : examQs) {
			query = "SELECT correct_option_no FROM question where question_id = ?";
			try {
				System.out.println("QId = " + q.get("question_id"));
				correctOption = template.queryForObject(query, Integer.class, q.get("question_id"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (Integer.parseInt(q.get("option_entered").toString()) == correctOption)
				query = "UPDATE exam_question SET is_correct = 1 where exam_question_id = ?";
			else
				query = "UPDATE exam_question SET is_correct = 0 where exam_question_id = ?";
			isUpdated = template.update(query, q.get("exam_question_id"));
			if (isUpdated == 0) {
				status = false;
				break;
			} else
				status = true;
		}
		if (status) {
			// if (studentDAO.getStudentType(userId) == 2) {
			query = "UPDATE test t inner join exam e on t.test_id = e.test_id"
					+ " SET is_written = 1 where e.exam_id = ?";
			isUpdated = template.update(query, id);
			// } else if (studentDAO.getStudentType(userId) == 1) {
			// query = "INSERT INTO
			// student_question_paper(user_id,question_paper_id) VALUES
			// (?,?)";
			// isUpdated = template.update(query, userId, id);
		}
		return status;
	}

	public boolean updateExamResult(int examId) {
		boolean status = false;
		String query = "SELECT sum(no_of_correct_questions) as correct_count,"
				+ " sum(no_of_wrong_questions) as wrong_count, sum(total_attended) as total_attended,"
				+ " sum(correct_mark) as correct_mark, sum(negative_mark) as negative_mark,"
				+ " sum(total_mark) as total_mark FROM exam_category_mark WHERE exam_id = ?";
		List<Map<String, Object>> result = template.queryForList(query, examId);
		Map<String, Object> marks = result.get(0);
		query = "SELECT sum(total_time) FROM exam_category_time WHERE exam_id = ?";
		int time = template.queryForObject(query, Integer.class, examId);
		query = "UPDATE exam SET question_answered = ?, question_correct_count = ?, question_wrong_count = ?,"
				+ "mark_correct = ?, mark_negative = ?, total_mark = ?, total_time = ? WHERE exam_id = ?";
		if (template.update(query, marks.get("total_attended"), marks.get("correct_count"), marks.get("wrong_count"),
				marks.get("correct_mark"), marks.get("negative_mark"), marks.get("total_mark"), time, examId) > 0)
			status = true;
		else
			status = false;
		return status;
	}

}
