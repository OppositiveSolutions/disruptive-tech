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

	public List<Map<String,Object>> getAllExams(int userId) {
		String query = "SELECT t.* FROM exam e inner join test t on e.test_id = t.test_id where user_id = ? and is_written = 1";
		return template.queryForList(query, userId);
	}

	public List<Map<String, Object>> getAllTests(int userId) {
		String query = "SELECT * FROM test where user_id = ? and is_written = 0";
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

}
