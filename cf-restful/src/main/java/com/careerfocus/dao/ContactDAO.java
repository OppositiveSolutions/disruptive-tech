package com.careerfocus.dao;

import com.careerfocus.constants.Constants;
import com.careerfocus.entity.States;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class ContactDAO {

	private JdbcTemplate template;

	@Autowired
	public ContactDAO(JdbcTemplate template) {
		this.template = template;
	}

	@Autowired
	MailDAO mailDAO;

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

	public boolean saveInquiry(String emailId, String phone, String content) {
		String query = "INSERT INTO inquiry(email_id,phone,content) VALUES (?,?,?)";
		boolean status = template.update(query, emailId, phone, content) > 0 ? true : false;
		if (status)
			try {
				mailDAO.sendContentMail(emailId, phone, content);
			} catch (MalformedURLException | EmailException e) {
				e.printStackTrace();
			}
		return status;
	}

}
