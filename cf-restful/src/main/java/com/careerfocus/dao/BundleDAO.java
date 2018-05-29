package com.careerfocus.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class BundleDAO {

	private JdbcTemplate template;

	@Autowired
	public BundleDAO(JdbcTemplate template) {
		this.template = template;
	}

	public List<Map<String, Object>> getBundles() {
		String query = "SELECT bundle_id as bundleId, name, description, mrp, selling_price as sellingPrice,"
				+ " is_available as isAvailavble, discount_percent as discountPercent, coaching_type as coachingType,"
				+ " created_date as createdDate, validity_days as validityDays, img_file_name as imgFileName FROM bundle";
		return template.queryForList(query);
	}

	public List<Map<String, Object>> getQPBundleList(int coachingType) {
		String query = "select b.*,1 as is_added_to_cart from bundle b"
				+ " inner join cart c on b.bundle_id = c.bundle_id"
				+ " where coaching_type = ? and c.user_id = 1 and c.bundle_id is not null"
				+ " union select b.*,0 as is_added_to_cart from bundle b"
				+ " left join cart c on b.bundle_id = c.bundle_id"
				+ " where coaching_type = ? and c.bundle_id is null group by bundle_id";
		return template.queryForList(query, coachingType, coachingType);
	}

	public List<Map<String, Object>> getCoachingTypeList() {
		String query = "select coaching_type_id as coachingTypeId, name from coaching_type";
		return template.queryForList(query);
	}

	public List<Map<String, Object>> getPurchasedQPBundleList(int userId) {
		String query = "select * from bundle b inner join bundle_purchase bp"
				+ " on b.bundle_id = bp.bundle_id where bp.user_id = ?";
		return template.queryForList(query, userId);
	}

	public List<Map<String, Object>> getBundleQPs(int bundleId) {
		String query = "select bqp.bundle_id, bqp.question_paper_id, qp.name, qp.duration,"
				+ " qp.no_of_questions from bundle_question_paper bqp"
				+ " inner join question_paper qp on bqp.question_paper_id = qp.question_paper_id where bundle_id = ?";
		return template.queryForList(query, bundleId);
	}

	public int addQptoBundle(int bundleId, int qpId) {
		String query = "insert into bundle_question_paper (bundle_id,question_paper_id) values(?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bundleId);
			ps.setInt(2, qpId);
			return ps;
		}, holder);
		return holder.getKey().intValue();
	}

	public int removeQpFromBundle(Integer bundleId, Integer qpId) {
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
