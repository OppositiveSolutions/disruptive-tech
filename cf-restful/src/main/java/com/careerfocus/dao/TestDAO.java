package com.careerfocus.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {

	@Autowired
	private JdbcTemplate template;

	public TestDAO(JdbcTemplate template) {
		this.template = template;
	}

	public List<Map<String, Object>> getAllExams(int userId) {
		String query = "SELECT t.* FROM exam e inner join test t on e.test_id = t.test_id where user_id = ? and is_written = 1";
		return template.queryForList(query, userId);
	}

	public List<Map<String, Object>> getAllTests(int userId) {
		String query = "SELECT t.test_id as testId, t.bundle_question_paper_id as bundleQPId, t.expiry_date as expiryDate,"
				+ " IFNULL(t.is_demo, 0) as isDemo, qp.name,qp.exam_code FROM test t inner join bundle_question_paper bqp"
				+ " on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
				+ " inner join question_paper qp on qp.question_paper_id = bqp.question_paper_id"
				+ " where user_id = ? and is_written = 0 and qp.status = 1";
		return template.queryForList(query, userId);
	}

	public boolean createTest(int bundlePurchaseId) {
		boolean status = true;
		try {
			String query = "INSERT INTO test (bundle_question_paper_id,user_id,expiry_date,is_enabled,is_written,is_demo)"
					+ " SELECT bundle_question_paper_id,user_id,expiry_date,'1','0',is_demo FROM bundle_purchase bp"
					+ " inner join bundle_question_paper bqp on bp.bundle_id = bqp.bundle_id where bundle_purchase_id = ?";
			template.update(query, bundlePurchaseId);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean createTestDefault(int bundleQPId, boolean isDemo) {
		boolean status = true;
		try {
			String query = "insert into test (bundle_question_paper_id,user_id,expiry_date,is_enabled,is_written)"
					+ " select ?, u.user_id, now(), 1, 0 from user u";
			if (!isDemo)
				query += " inner join student s on s.user_id = u.user_id where s.type = 1 and u.role = 1";
			else
				query += " where u.role = 1";
			template.update(query, bundleQPId);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean createTestDefaultForANewUser(int bundleId, int userId, int isDemo) {
		boolean status = true;
		try {
			String query = "INSERT INTO test (bundle_question_paper_id,user_id,expiry_date,is_enabled,is_written,is_demo)"
					+ " SELECT bundle_question_paper_id, ?, now(), '1', '0', qp.is_demo"
					+ " FROM bundle_question_paper bqp inner join question_paper qp on bqp.question_paper_id = qp.question_paper_id"
					+ " where bundle_id = ? and qp.status = 1";
			if (isDemo == 1)
				query += " and qp.is_demo = 1";
			template.update(query, userId, bundleId);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public List<Map<String, Object>> getCategoriesOfATest(int testId) {
		String query = "select c.category_id,c.name,qpc.no_of_questions,qpc.correct_answer_mark,"
				+ " qpc.negative_mark,qpc.duration from category c"
				+ " inner join question_paper_category qpc on qpc.category_id = c.category_id"
				+ " inner join question_paper qp on qp.question_paper_id = qpc.question_paper_id"
				+ " inner join bundle_question_paper bqp on bqp.question_paper_id = qp.question_paper_id"
				+ " inner join test t on t.bundle_question_paper_id = bqp.bundle_question_paper_id"
				+ " where t.test_id = ?";
		return template.queryForList(query, testId);
	}

}
