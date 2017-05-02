package com.careerfocus.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.careerfocus.entity.States;

@Repository
public class CommonDAO {

	@Autowired
	JdbcTemplate template;

	public List<States> getStates() {

		String query = "SELECT * FROM states";

		return template.query(query, new RowMapper<States>() {

			@Override
			public States mapRow(ResultSet result, int arg1) throws SQLException {
				return new States(result.getInt("state_id"), result.getString("name"));
			}

		});
	}

}
