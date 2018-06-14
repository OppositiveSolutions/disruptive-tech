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
	
	@Autowired
	CommonDAO commonDAO;

	public boolean changePassword(int userId, String password) {
		String query = "UPDATE user SET password = ? WHERE user_id  = ?";
		return template.update(query, password, userId) > 0 ? true : false;
	}

	public boolean resetPassword(String username, String password) {
		String query = "UPDATE user SET password = ? WHERE username  = ?";
		boolean status = template.update(query, password, username) > 0 ? true : false;
		if (status)
			try {
				mailDAO.welcomeMailUser(username, password, "Password Reset for Career Focus Account.", commonDAO.getStatusFromEmailId(username));
			} catch (MalformedURLException | EmailException e) {
				e.printStackTrace();
			}
		return status;
	}

	public boolean saveInquiry(String contact, String name, String content) {
		String query = "INSERT INTO inquiry(contact,name,content) VALUES (?,?,?)";
		boolean status = template.update(query, contact, name, content) > 0 ? true : false;
		if (status)
			try {
				mailDAO.sendInquiryMail(contact, name, content);
			} catch (MalformedURLException | EmailException e) {
				e.printStackTrace();
			}
		return status;
	}

}
