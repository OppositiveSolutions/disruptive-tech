package com.careerfocus.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.careerfocus.entity.Bundle;

@Repository
public class BundleDAO {

	private JdbcTemplate template;

	@Autowired
	public BundleDAO(JdbcTemplate template) {
		this.template = template;
	}

	public List<Map<String, Object>> getQPBundleList(int coachingType) {
		String query = "select * from bundle where coaching_type = ?";
		return template.queryForList(query, coachingType);
	}

	public List<Map<String,Object>> getBundleQPs(int bundleId) {
		String query = "select bqp.bundle_id, bqp.question_paper_id, qp.name, qp.duration,"
				+ " qp.no_of_questions from bundle_question_paper bqp"
				+ " inner join question_paper qp on bqp.question_paper_id = qp.question_paper_id where bundle_id = ?";
		return template.queryForList(query, bundleId);
	}

	public int addQptoBundle(int bundleId, int qpId) {
		String query = "insert into bundle_question_paper (bundle_id,question_paper_id) values(?,?)";
		return template.update(query, bundleId, qpId);
	}

	public int removeQpfromBundle(Integer bundleId, Integer qpId) {
		String query = "DELETE FROM bundle_question_paper WHERE question_paper_id = ? and bundle_id  = ?"; 
		return template.update(query, qpId, bundleId);
	}

	public int purchaseBundle(int userId, int bundleId) {
		String query = "insert into bundle_purchase (bundle_id,user_id,purchase_time,purchase_price,expiry_date) values"
				+ "(?,?,now(),(select selling_price from bundle where bundle_id = ?),"
				+ "(now() + INTERVAL (select validity_days from bundle where bundle_id = ?) DAY))";
		return template.update(query, bundleId, userId, bundleId, bundleId);
	}

}
