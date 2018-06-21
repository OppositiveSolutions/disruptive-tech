package com.careerfocus.dao;

import com.careerfocus.constants.Constants;
import com.careerfocus.entity.Address;
import com.careerfocus.entity.User;
import com.careerfocus.entity.UserPhone;
import com.careerfocus.model.response.StudentVO;
import com.careerfocus.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDAO {

	@Autowired
	private JdbcTemplate template;

	// private final int USER_ACTIVE = 1;
	// private final int USER_DEACTIVATED = 0;

	public List<Map<String, Object>> getStudents(int pageSize, int pageNo, int type) {

		String query = "SELECT u.user_id as userId, first_name as firstName, last_name as lastName,"
				+ " created_date as createdDate, expiry_date as expiryDate, username, u.status, dob,"
				+ " qualification, gender, street_address as streetAdress, place, city,"
				+ " state_id as stateId, pin_code as pinCode, phone_no as phoneNo FROM student s"
				+ " INNER JOIN user u ON s.user_id = u.user_id and u.role = 1"
				+ " LEFT JOIN user_phone up on s.user_id = up.user_id and up.is_primary = 1"
				+ " LEFT JOIN address a on s.user_id = a.user_id WHERE s.status != " + Constants.STUDENT_DELETED
				+ " AND u.status = " + type;
		if (type == 0)
			query += " AND (s.type != " + Constants.STUDENT_REGISTERED
					+ " OR (u.created_date BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW() AND s.type = " + Constants.STUDENT_REGISTERED + "))";
		query += " GROUP BY s.user_id ORDER BY first_name, last_name";

		if (pageSize > 0 && pageNo > 0) {
			query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		}

		return template.query(query, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Object> map = new HashMap<>();
				map.put("userId", rs.getInt("userId"));
				map.put("status", rs.getString("status"));
				map.put("firstName", rs.getString("firstName"));
				map.put("lastName", rs.getString("lastName"));
				if (rs.getDate("createdDate") != null)
					map.put("createdDate", DateUtils.toFormat(rs.getDate("createdDate"), "MM/dd/yyyy"));
				if (rs.getDate("expiryDate") != null)
					map.put("expiryDate", DateUtils.toFormat(rs.getDate("expiryDate"), "MM/dd/yyyy"));
				map.put("username", rs.getString("username"));
				map.put("streetAdress", rs.getString("streetAdress"));
				map.put("qualification", rs.getString("qualification"));
				map.put("place", rs.getString("place"));
				map.put("dob", rs.getDate("dob") != null ? DateUtils.toFormat(rs.getDate("dob"), "MM/dd/yyyy") : null);
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
				+ " created_date as createdDate, username, u.status, dob, gender, street_address as streetAdress,"
				+ " place, city, state_id as stateId, pin_code as pinCode, phone_no as phoneNo"
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
				map.put("createdDate", rs.getDate("createdDate") != null
						? DateUtils.toFormat(rs.getDate("createdDate"), "MM/dd/yyyy") : null);
				map.put("username", rs.getString("username"));
				map.put("streetAdress", rs.getString("streetAdress"));
				map.put("place", rs.getString("place"));
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
		return template.update(query, userId) > 0 ? true : false;
	}

	public boolean deleteUserPhone(int userId) {
		String query = "delete from user_phone where user_id = ?";
		return template.update(query, userId) > 0 ? true : false;
	}

	public int activateStudent(int userId) {
		String query = "update user set status = 1 - status where user_id = ?";
		if (template.update(query, userId) > 0) {
			query = "update student set type = " + Constants.STUDENT_REG_AND_ONCE_ACTIVE
					+ " where user_id = ? AND type = " + Constants.STUDENT_REGISTERED;
			template.update(query, userId);
			query = "select status from user where user_id = ?";
			return template.queryForObject(query, Integer.class, userId);
		}
		return 0;
	}

	public boolean deleteStudent(int userId) {
		String query = "update student set status = 2 where user_id = ?";
		return template.update(query, userId) > 0 ? true : false;
	}

	public int getStudentType(int userId) {
		String query = "select type from student where user_id = ?";
		int type = 1;
		type = template.queryForObject(query, Integer.class);
		return type;
	}

	public User saveUser(User user) {
		String query = "insert into user (username, password, created_date, role, first_name,"
				+ " last_name, dob, gender, status) values (?,?,now(),?,?,?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getRole());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getLastName());
			ps.setDate(6, new java.sql.Date(user.getDob().getTime()));
			ps.setString(7, user.getGender());
			ps.setInt(8, user.getStatus());
			return ps;
		}, holder);
		user.setUserId(holder.getKey().intValue());
		return user;
	}

	public boolean editUser(User user) {
		String query = "UPDATE user SET username =?, first_name = ?,"
				+ " last_name = ?, dob = ?, gender = ? WHERE user_id = ?";
		return template.update(query, user.getUsername(), user.getFirstName(), user.getLastName(),
				new java.sql.Date(user.getDob().getTime()), user.getGender(), user.getUserId()) > 0 ? true : false;
	}

	public Address saveAddress(Address address) {
		String query = "insert into address (user_id, street_address, place, city, state_id, pin_code"
				+ " ) values (?,?,?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, address.getUser().getUserId());
			ps.setString(2, address.getStreetAddress());
			ps.setString(3, address.getPlace());
			ps.setString(4, address.getCity());
			ps.setInt(5, address.getStates().getStateId());
			ps.setInt(6, address.getPinCode());
			return ps;
		}, holder);
		address.setAddressId(holder.getKey().intValue());
		return address;
	}

	public UserPhone savePhone(UserPhone phone) {
		String query = "insert into user_phone (user_id, type, phone_no, is_primary) values (?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, phone.getUser().getUserId());
			ps.setInt(2, phone.getType());
			ps.setString(3, phone.getPhoneNo());
			ps.setBoolean(4, phone.isPrimary());
			return ps;
		}, holder);
		phone.setUserPhoneId(holder.getKey().intValue());
		return phone;
	}
}
