package com.careerfocus.dao;

import com.careerfocus.model.response.StudentVO;
import com.careerfocus.util.DateUtils;
import com.google.common.collect.ImmutableMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDAO {

	@Autowired
	private JdbcTemplate template;

	public List<Map<String, Object>> getStudents(int pageSize, int pageNo) {

		String query = "SELECT u.user_id as userId, first_name as firstName, last_name as lastName,"
				+ " created_date as createdDate, expiry_date as expiryDate, username, status, dob,"
				+ " gender,street_address as streetAdress, land_mark as landMark, city,"
				+ " state_id as stateId, pin_code as pinCode, phone_no as phoneNo FROM student s"
				+ " INNER JOIN user u ON s.user_id = u.user_id"
				+ " LEFT JOIN user_phone up on s.user_id = up.user_id and up.is_primary = 1"
				+ " LEFT JOIN user_address ua on s.user_id = ua.user_id "
				+ " LEFT JOIN address a on ua.address_id = a.address_id ORDER BY first_name, last_name ";

		if (pageSize > 0 && pageNo > 0) {
			query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		}

		return template.query(query, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Map<String, Object> map = new HashMap<>();
				map.put("userId", rs.getInt("userId"));
				map.put("firstName", rs.getString("firstName"));
				map.put("lastName", rs.getString("lastName"));
				map.put("createdDate", DateUtils.toFormat(rs.getDate("createdDate"), "MM/dd/yyyy"));
				map.put("expiryDate", DateUtils.toFormat(rs.getDate("expiryDate"), "MM/dd/yyyy"));
				map.put("username", rs.getString("username"));
				map.put("streetAdress", rs.getString("streetAdress"));
				map.put("landMark", rs.getString("landMark"));
				map.put("city", rs.getString("city"));
				map.put("stateId", rs.getInt("stateId"));
				map.put("pinCode", rs.getInt("pinCode"));
				map.put("phoneNo", rs.getString("phoneNo"));
				return map;
			}
		});
	}

	public List<Map<String, Object>> getStaffs(int pageSize, int pageNo) {

		String query = "SELECT u.user_id as userId, first_name as firstName, last_name as lastName,"
				+ " created_date as createdDate, username, status, dob, gender, street_address as streetAdress,"
				+ " land_mark as landMark, city, state_id as stateId, pin_code as pinCode, phone_no as phoneNo"
				+ " FROM staff s INNER JOIN user u ON s.user_id = u.user_id"
				+ " LEFT JOIN user_phone up on s.user_id = up.user_id and up.is_primary = 1"
				+ " LEFT JOIN user_address ua on s.user_id = ua.user_id "
				+ " LEFT JOIN address a on ua.address_id = a.address_id ORDER BY first_name, last_name ";

		if (pageSize > 0 && pageNo > 0) {
			query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		}

		return template.query(query, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Map<String, Object> map = new HashMap<>();
				map.put("userId", rs.getInt("userId"));
				map.put("firstName", rs.getString("firstName"));
				map.put("lastName", rs.getString("lastName"));
				map.put("createdDate", DateUtils.toFormat(rs.getDate("createdDate"), "MM/dd/yyyy"));
				map.put("username", rs.getString("username"));
				map.put("streetAdress", rs.getString("streetAdress"));
				map.put("landMark", rs.getString("landMark"));
				map.put("city", rs.getString("city"));
				map.put("stateId", rs.getInt("stateId"));
				map.put("pinCode", rs.getInt("pinCode"));
				map.put("phoneNo", rs.getString("phoneNo"));
				return map;
			}
		});
	}

	public Collection<StudentVO> searchStudentsByName(String name, int pageSize, int pageNo) {
		String query = "SELECT u.user_id, first_name, last_name, created_date, expiry_date, username, status FROM student s \n"
				+ "INNER JOIN user u ON s.user_id = u.user_id WHERE role=1 AND CONCAT(first_name, ' ', last_name) LIKE ? "
				+ "ORDER BY first_name, last_name";

		if (pageSize > 0 && pageNo > 0) {
			query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		}
		return StudentVO(query, "%" + name + "%");
	}

	private Collection<StudentVO> StudentVO(String query, Object... params) {
		return template.query(query, params, (result, arg1) -> {
			return null;
		});
	}

	public boolean deleteUserAddress(int userId) {
		String query = "delete from user_address where user_id = ?";
		return template.update(query) > 0 ? true : false;
	}

	public int getStudentType(int userId) {
		String query = "select type from student where user_id = ?";
		return template.queryForObject(query, Integer.class);
	}
}
