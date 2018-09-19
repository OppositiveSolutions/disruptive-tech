package com.careerfocus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ResultDAO {

	private JdbcTemplate template;

	@Autowired
	public ResultDAO(JdbcTemplate template) {
		this.template = template;
	}

	public List<Map<String, Object>> getScoreCard(int userId) {
		String query = "SELECT exam_id as examId,qp.name as examName,qp.exam_code as examCode,"
				+ " TIMESTAMPDIFF(SECOND, start_time, end_time) div 60 as timeMin,"
				+ " TIMESTAMPDIFF(SECOND, start_time, end_time) mod 60 as timeSec,question_answered as totalAttended,"
				+ " question_correct_count as correctAnswerCount, question_wrong_count as wrongAnswerCount,"
				+ " mark_correct as correctMark, mark_negative as negativeMark, e.is_demo as isDemo,"
				+ " (total_mark/(qpc.no_of_questions * qpc.correct_answer_mark)) * 100 as percentage,"
				+ " total_mark as totalMark FROM exam e inner join test t on t.test_id = e.test_id"
				+ " inner join bundle_question_paper bqp on bqp.bundle_question_paper_id = t.bundle_question_paper_id"
				+ " inner join question_paper_category qpc on qpc.question_paper_id = bqp.question_paper_id"
				+ " inner join question_paper qp on qp.question_paper_id = bqp.question_paper_id"
				+ " where t.user_id = ? group by exam_id order by e.start_time desc";
		List<Map<String, Object>> scoreCardList = template.queryForList(query, userId);
		List<Map<String, Object>> returnScoreCardList = new ArrayList<Map<String, Object>>();
		Map<String, Object> scoreCard = new HashMap<String, Object>();
		for (Map<String, Object> t : scoreCardList) {
			scoreCard = getExamAttendedAndTotalCount(Integer.parseInt(t.get("examId").toString()));
			int correctAnswerCount = t.get("correctAnswerCount") != null
					? Integer.parseInt(t.get("correctAnswerCount").toString())
					: 0;
			int wrongAnswerCount = t.get("wrongAnswerCount") != null
					? Integer.parseInt(t.get("wrongAnswerCount").toString())
					: 0;
			t.put("totalAttended", correctAnswerCount + wrongAnswerCount);
			t.put("totalNoOfQuestions", scoreCard.get("totalNoOfQuestions"));
			returnScoreCardList.add(t);
		}
		return returnScoreCardList;
	}

	public Map<String, Object> getExamAttendedAndTotalCount(int examId) {
		String query = "SELECT question_answered as totalAttended, qpc.no_of_questions as noOfQuestions from exam_category_mark ecm"
				+ " inner join exam e on ecm.exam_id = e.exam_id inner join test t on t.test_id = e.test_id"
				+ " inner join bundle_question_paper bqp on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
				+ " inner join question_paper qp on bqp.question_paper_id = qp.question_paper_id "
				+ " inner join question_paper_category qpc on qpc.question_paper_id = qp.question_paper_id"
				+ " and ecm.category_id = qpc.category_id WHERE ecm.exam_id = ?";
		List<Map<String, Object>> categoryMarkList = template.queryForList(query, examId);
		int totalAttended = 0;
		int totalNoOfQuestions = 0;
		for (Map<String, Object> t : categoryMarkList) {
			int noOfQuestions = 0;
			int attended = 0;
			try {
				noOfQuestions = t.get("noOfQuestions") != null ? Integer.parseInt(t.get("noOfQuestions").toString())
						: 0;
				attended = t.get("totalAttended") != null ? Integer.parseInt(t.get("totalAttended").toString()) : 0;
			} catch (Exception e) {
				e.printStackTrace();
				noOfQuestions = 0;
			}
			try {
				if (attended > noOfQuestions)
					totalAttended += noOfQuestions;
				else
					totalAttended += attended;
				totalNoOfQuestions += noOfQuestions;
			} catch (Exception e) {
				e.printStackTrace();
				totalAttended = 0;
			}
		}
		Map<String, Object> count = new HashMap<String, Object>();
		count.put("totalNoOfQuestions", totalNoOfQuestions);
		count.put("totalAttended", totalAttended);
		return count;
	}

	public List<Integer> getExamCategoryTime(int examId, int categoryId) {
		String query = "SELECT total_time from exam_category_mark WHERE exam_id = ? AND category_id = ?";
		List<Integer> categoryTimeList = template.queryForList(query, Integer.class, examId, categoryId);
		return categoryTimeList;
	}

	public List<Map<String, Object>> getExamCategoriesScoreList(int examId) {
		String query = "SELECT * from exam_category_mark WHERE exam_id = ?";
		List<Map<String, Object>> categoryMarkList = template.queryForList(query, examId);
		return categoryMarkList;
	}

	public List<Map<String, Object>> getExamCategoriesScoreGraph(int examId) {
		String query = "SELECT e.exam_id as examId,qp.name as examName,qp.exam_code as examCode,"
				+ " c.category_id as categoryId, c.name as categoryName,ect.total_time div 60 as timeTakenMin,"
				+ " ect.total_time mod 60 as timeTakenSec,qpc.duration as durationMin,ecm.correct_mark as correctMark,"
				+ " ecm.negative_mark as negativeMark,ecm.total_mark as totalMark,"
				+ " qpc.no_of_questions * qpc.correct_answer_mark as totalPossibleMark, ecm.total_attended as totalAttended,"
				+ " qpc.no_of_questions as noOfQuestions, qpc.negative_mark as negativeMarkPerAnswer,"
				+ " qpc.correct_answer_mark as correctMarkPerAnswer FROM exam_category_mark ecm"
				+ " inner join exam e on e.exam_id = ecm.exam_id inner join test t on t.test_id = e.test_id"
				+ " inner join bundle_question_paper bqp on bqp.bundle_question_paper_id = t.bundle_question_paper_id"
				+ " inner join question_paper_category qpc on qpc.question_paper_id = bqp.question_paper_id"
				+ " inner join question_paper qp on qp.question_paper_id = bqp.question_paper_id"
				+ " inner join category c on ecm.category_id = c.category_id"
				+ " inner join exam_category_time ect on ect.exam_id = ecm.exam_id and ect.category_id = ecm.category_id"
				+ " where ecm.exam_id = ? group by ecm.category_id";
		List<Map<String, Object>> categoryMarkList = template.queryForList(query, examId);
		List<Map<String, Object>> returnCategoryMarkList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> t : categoryMarkList) {
			int noOfQuestions = Integer.parseInt(t.get("noOfQuestions").toString());
			if (Integer.parseInt(t.get("totalAttended").toString()) > noOfQuestions) {
				t.put("totalAttended", noOfQuestions);
			}
			try {
				if (Double.parseDouble(t.get("correctMark").toString()) > Double
						.parseDouble(t.get("totalPossibleMark").toString())) {
					Double totalPossibleMark = Double.parseDouble(t.get("totalPossibleMark").toString());
					Double negativeMark = Double.parseDouble(t.get("negativeMark").toString());
					Double negativeMarkPerAnswer = Double.parseDouble(t.get("negativeMarkPerAnswer").toString());
					Double correctMarkPerAnswer = Double.parseDouble(t.get("correctMarkPerAnswer").toString());
					t.put("correctMark",
							totalPossibleMark - ((negativeMark / negativeMarkPerAnswer) * correctMarkPerAnswer));
					t.put("totalMark", Double.parseDouble(t.get("correctMark").toString())
							- Double.parseDouble(t.get("negativeMark").toString()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			returnCategoryMarkList.add(t);
		}
		return returnCategoryMarkList;
	}

	public List<Map<String, Object>> getTopTenScore(int examId) {
		String query = "SELECT total_mark as mark,concat(u.first_name, ' ', u.last_name) as name FROM exam e"
				+ " inner join test t on t.test_id = e.test_id inner join user u on t.user_id = u.user_id"
				+ " inner join bundle_question_paper bqp on bqp.bundle_question_paper_id = t.bundle_question_paper_id"
				+ " inner join question_paper qp on qp.question_paper_id = bqp.question_paper_id"
				+ " where bqp.bundle_question_paper_id = (select bqp1.bundle_question_paper_id from bundle_question_paper bqp1"
				+ " inner join test t1 on t1.bundle_question_paper_id = bqp1.bundle_question_paper_id"
				+ " inner join exam e1 on e1.test_id = t1.test_id where e1.exam_id = ?) order by mark desc limit 10";
		List<Map<String, Object>> topTenList = template.queryForList(query, examId);
		return topTenList;
	}

	public double getUserAccuracy(int userId) {
		String query = "select (sum(question_correct_count) / sum(question_answered)) * 100 as accuracy"
				+ " from exam e inner join test t on t.test_id = e.test_id WHERE t.user_id = ?";
		Double value = template.queryForObject(query, Double.class, userId);
		return value != null ? value : 0.0;
	}

	public double getUserAverageTimePerQuestion(int userId) {
		String query = "select sum(total_time) / sum(question_answered) as time"
				+ " from exam e inner join test t on t.test_id = e.test_id"
				+ " inner join bundle_question_paper bqp on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
				+ " inner join bundle_purchase bp on bqp.bundle_id = bp.bundle_id WHERE bp.user_id = ?";
		Double value = template.queryForObject(query, Double.class, userId);
		return value != null ? value : 0.0;
	}

	public double getUserTestCount(int userId) {
		String query = "select count(t.test_id) as count from test t"
				+ " left join exam e on t.test_id = e.test_id WHERE t.user_id = ?";
		return template.queryForObject(query, Integer.class, userId);
	}

	public double getUserExamCount(int userId) {
		String query = "select count(e.exam_id) as count from exam e"
				+ " inner join test t on t.test_id = e.test_id WHERE t.user_id = ?";
		return template.queryForObject(query, Integer.class, userId);
	}

}
