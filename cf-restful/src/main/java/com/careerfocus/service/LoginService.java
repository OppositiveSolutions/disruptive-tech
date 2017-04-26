package com.careerfocus.service;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.stereotype.Service;

import com.careerfocus.constants.Constants;
import com.careerfocus.entity.User;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.response.Response;

@Service
public class LoginService {

	private final Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings({ "rawtypes" })
	@Autowired
	private FindByIndexNameSessionRepository sessionRepository;

	@Autowired
	FindByIndexNameSessionRepository<? extends ExpiringSession> sessions;

	@Autowired
	RedisTemplate<String, ExpiringSession> redisTemplate;

	@Autowired
	UserRepository userRepository;

	public User performLogin(String username, String password, HttpServletRequest request) {
		logger.info("uesrname: " + username);
		logger.info("password: " + password);

		User user = userRepository.findByUsernameAndPassword(username, password);
		if (user == null || user.getUserId() == Constants.FALSE) {
			throw new AuthenticationCredentialsNotFoundException("");
		}

		logger.info("user: " + user.toString());

		HttpSession session = request.getSession(true);
		
		initSessionAtrributes(session, user);

		logger.info("LOGGING IN...");

		return user;
	}

	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
	}

	public void logoutAllDevices(HttpServletRequest request) {

		HttpSession currentSession = request.getSession(false);
		if (currentSession == null || !request.isRequestedSessionIdValid())
			return;
		String userId = currentSession.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME)
				.toString();

		@SuppressWarnings("unchecked")
		SpringSessionBackedSessionRegistry sessionRegistry = new SpringSessionBackedSessionRegistry(sessionRepository);

		Collection<? extends ExpiringSession> usersSessions = sessions
				.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, userId)
				.values(); // gets all sessions of the user

		usersSessions.forEach(session -> { // expiring/removing each session
			String sessionId = session.getId();
//			sessionRegistry.removeSessionInformation(sessionId);
			
			SessionInformation info = sessionRegistry.getSessionInformation(sessionId);
			info.expireNow();
			// logger.info("sessionId: " + sessionId);
			// redisTemplate.delete("spring:session:sessions:" + sessionId);
			// redisTemplate.delete("spring:session:sessions:expires:" +
			// sessionId);
		});
	}

	public void initSessionAtrributes(HttpSession session, User user) {
		session.setMaxInactiveInterval(-1);

		session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, user.getUserId() + "");
		session.setAttribute("role", user.getRole());
		session.setAttribute("firstName", user.getFirstName());
		session.setAttribute("lastName", user.getLastName());
		user.setPassword(null);
	}

}
