package com.careerfocus.dao;

import com.careerfocus.service.QuestionPaperService;
import com.careerfocus.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionPaperDAO {

	// private static final Logger log =
	// Logger.getLogger(DateUtils.class.getClass());

	private JdbcTemplate jdbcTemplate;

	@Autowired
	TestDAO testDAO;

	@Autowired
	public QuestionPaperDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void updateLastModified(List<Integer> qIds) {

		String query = "UPDATE `career_focus`.`question_paper` SET `last_modified` = current_timestamp() WHERE `question_paper_id`=?";

		jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, qIds.get(i));
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

		StringBuilder query = new StringBuilder("UPDATE career_focus.question_paper qp "
				+ "INNER JOIN question_paper_category qpc ON qp.question_paper_id=qpc.question_paper_id "
				+ "INNER JOIN question_paper_sub_category qpsc ON qpc.question_paper_category_id=qpsc.question_paper_category_id "
				+ "SET qp.`last_modified` = current_timestamp() " + "WHERE qpsc.`question_paper_sub_category_id` IN ("
				+ DBUtils.getMarkers(ids.size()) + ")");

		jdbcTemplate.update(query.toString(), ids.toArray());
	}

	public List<Map<String, Object>> getAllQuestionPapers(int coachingType) {
		String query = "SELECT question_paper_id as qpId, name, exam_code as examCode FROM question_paper";
		if (coachingType > 0) {
			query += " where coaching_type = ?";
			return jdbcTemplate.queryForList(query, coachingType);
		} else {
			return jdbcTemplate.queryForList(query);
		}
	}

	public Map<String, Object> changeQPStatus(int questionPaperId, int status) {
		String query = "UPDATE question_paper set status = ? WHERE question_paper_id = ?";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (jdbcTemplate.update(query, status, questionPaperId) > 0) {
			returnMap.put("status", true);
			if (status == QuestionPaperService.BUNDLE_STATUS_ENABLED)
				returnMap.put("message", "Successfully Enabled Question Paper.");
			else if (status == QuestionPaperService.BUNDLE_STATUS_DISABLED)
				returnMap.put("message", "Successfully Disabled Question Paper.");
			else if (status == QuestionPaperService.BUNDLE_STATUS_DELETED)
				returnMap.put("message", "Successfully Deleted Question Paper.");
		} else {
			returnMap.put("status", false);
			if (status == QuestionPaperService.BUNDLE_STATUS_ENABLED)
				returnMap.put("message", "Failed to Enable Question Paper.");
			else if (status == QuestionPaperService.BUNDLE_STATUS_DISABLED)
				returnMap.put("message", "Failed to Disable Question Paper.");
			else if (status == QuestionPaperService.BUNDLE_STATUS_DELETED)
				returnMap.put("message", "Failed to Delete Question Paper.");
		}
		return returnMap;
	}

	public Map<String, Object> changeQPIsDemo(int questionPaperId, int isDemo) {
		String query = "UPDATE question_paper set is_demo = ? WHERE question_paper_id = ?";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (jdbcTemplate.update(query, isDemo, questionPaperId) > 0) {
			int bundleQPId = getDefaultBundleQPId(questionPaperId, QuestionPaperService.DEFAUL_QP_BUNDLE);
			System.out.println("bundleQPId = " + bundleQPId);
			if (isDemo == 1) {
				if (bundleQPId > 0) {
					testDAO.createTestDefault(bundleQPId, true);
					testDAO.updateTestIsEnabled(bundleQPId, isDemo);
				}
			} else {
				if (bundleQPId > 0)
					testDAO.updateTestIsEnabled(bundleQPId, isDemo);
			}
			returnMap.put("status", true);
			if (isDemo == 1)
				returnMap.put("message", "Successfully set Question Paper as Demo.");
			else if (isDemo == 0)
				returnMap.put("message", "Successfully set Question Paper as Regular.");
		} else {
			returnMap.put("status", false);
			if (isDemo == 1)
				returnMap.put("message", "Failed to set Question Paper as Demo.");
			else if (isDemo == 0)
				returnMap.put("message", "Failed to set Question Paper as Regular.");
		}
		return returnMap;
	}

	private int getDefaultBundleQPId(int questionPaperId, int bundleId) {
		try {
			String query = "SELECT bundle_question_paper_id FROM bundle_question_paper WHERE question_paper_id = ? AND bundle_id = ?";
			return jdbcTemplate.queryForObject(query, Integer.class, questionPaperId, bundleId);
		} catch (Exception e) {
			return 0;
		}
	}

	public int getIsDemoQP(int questionPaperId) {
		String query = "SELECT is_demo FROM question_paper WHERE question_paper_id = ?";
		return jdbcTemplate.queryForObject(query, Integer.class, questionPaperId);
	}

	public int getQuestionPaperSubCategoryIdFromQuestionId(int questionId) {
		String query = "SELECT question_paper_sub_category_id FROM question_paper_question WHERE question_id = ?";
		return jdbcTemplate.queryForObject(query, Integer.class, questionId);
	}

	public int getQuestionNoFromQuestionId(int questionId) {
		String query = "SELECT question_no FROM question_paper_question WHERE question_id = ?";
		return jdbcTemplate.queryForObject(query, Integer.class, questionId);
	}

	public int getBundleQPId(int questionPaperId) {
		try {
			String query = "SELECT bundle_question_paper_id FROM bundle_question_paper WHERE question_paper_id = ?";
			return jdbcTemplate.queryForObject(query, Integer.class, questionPaperId);
		} catch (Exception e) {
			return 0;
		}
	}

}
