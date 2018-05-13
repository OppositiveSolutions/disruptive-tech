package com.careerfocus.dao;

import com.careerfocus.entity.States;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Repository
public class ProfileDAO {

	private JdbcTemplate template;

	@Autowired
	public ProfileDAO(JdbcTemplate template) {
		this.template = template;
	}

	@Autowired
	MailDAO mailDAO;

	@Autowired
	CommonDAO commonDAO;

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

	public Map<String, Object> getUserIdAndPasswordFromUniqueString(String uq_) {
		String query = "SELECT u.user_id, password from user u"
				+ " inner join user_unique_string uus on u.user_id = uus.user_id"
				+ " WHERE uus.unique_string = ? and expiry > now() order by expiry limit 1;";
		return template.queryForMap(query, uq_);
	}

	public boolean resetPassword(String emailId) {
		String uq_ = null;
		int userId = commonDAO.getUserIdFromEmailId(emailId);
		uq_ = createUniqueString(userId);
		if (emailId != null && uq_ != null) {
			try {
				mailDAO.sendPasswordResetLinkMail(emailId, uq_, "Reset Password for Career Focus Account.");
			} catch (MalformedURLException | EmailException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	private String createUniqueString(int userId) {
		String uq_ = randomString(20);
		if (saveUniqueString(uq_, userId))
			return uq_;
		else
			return null;
	}

	private boolean saveUniqueString(String uq_, int userId) {
		String query = "INSERT INTO user_unique_string (user_id,unique_string,expiry) VALUES(?,?,(NOW() + INTERVAL 1 DAY))";
		return template.update(query, userId, uq_) > 0 ? true : false;
	}

	public static String randomString(int length) {
		char[] characterSet = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		Random random = new SecureRandom();
		char[] result = new char[length];
		for (int i = 0; i < result.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(characterSet.length);
			result[i] = characterSet[randomCharIndex];
		}
		return new String(result);
	}

}
