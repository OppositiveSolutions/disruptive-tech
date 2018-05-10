package com.careerfocus.dao;

import com.careerfocus.entity.States;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class ProfileDAO {

	private JdbcTemplate template;

	@Autowired
	public ProfileDAO(JdbcTemplate template) {
		this.template = template;
	}

	@Autowired
	MailDAO mailDAO;

	public List<States> getStates() {

		String query = "SELECT * FROM states";

		return template.query(query,
				(ResultSet result, int arg1) -> new States(result.getInt("state_id"), result.getString("name")));
	}

	public String getDetailsForMyProfile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean changePassword(int userId, String password) {
		String query = "UPDATE user SET password = ? WHERE user_id  = ?";
		return template.update(query, password, userId) > 0 ? true : false;
	}

	public boolean resetPassword(String username, String password) {
		String query = "UPDATE user SET password = ? WHERE username  = ?";
		boolean status = template.update(query, password, username) > 0 ? true : false;
		if (status)
			try {
				mailDAO.welcomeMailUser(username, password, "Password Reset for Career Focus Account.");
			} catch (MalformedURLException | EmailException e) {
				e.printStackTrace();
			}
		return status;
	}

}
