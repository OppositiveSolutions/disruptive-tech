package com.careerfocus.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.careerfocus.model.response.StudentVO;

@Repository
public class StudentDAO {

	@Autowired
	JdbcTemplate template;

	public Collection<StudentVO> getStudents(int pageSize, int pageNo) {

		String query = "SELECT u.user_id, first_name, last_name, created_date, expiry_date, username, status FROM student s \n"
				+ "INNER JOIN user u ON s.user_id = u.user_id ORDER BY first_name, last_name";

		if (pageSize > 0 && pageNo > 0) {
			query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		}

		return StudentVO(query, new Object[] {});
	}

	public Collection<StudentVO> searchStudentsByName(String name, int pageSize, int pageNo) {
		String query = "SELECT u.user_id, first_name, last_name, created_date, expiry_date, username, status FROM student s \n"
				+ "INNER JOIN user u ON s.user_id = u.user_id WHERE role=1 AND CONCAT(first_name, ' ', last_name) LIKE ? "
				+ "ORDER BY first_name, last_name";

		if (pageSize > 0 && pageNo > 0) {
			query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
		}
		return StudentVO(query, new Object[] { "%" + name + "%" });
	}

	private Collection<StudentVO> StudentVO(String query, Object... params) {
		return template.query(query, params, new RowMapper<StudentVO>() {

			@Override
			public StudentVO mapRow(ResultSet result, int arg1) throws SQLException {
				String name = result.getString("first_name") + " " + result.getString("last_name");
				return new StudentVO(result.getInt("user_id"), name, result.getDate("created_date"),
						result.getDate("expiry_date"), result.getString("username"), null, result.getInt("status"));
			}

		});
	}
}
