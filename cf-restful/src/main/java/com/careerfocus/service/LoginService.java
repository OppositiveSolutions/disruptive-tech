package com.careerfocus.service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.stereotype.Service;

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
	RedisTemplate<String, String> redisTemplate;

	public Response performLogin(String username, String password, HttpServletRequest request) {
		logger.info("uesrname: " + username);
		logger.info("password: " + password);

		HttpSession session = request.getSession();

		session.setMaxInactiveInterval(-1);

		session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "sarathnagesh@gmail.com");
		session.setAttribute("role", 1);
		session.setAttribute("firstName", "Sarath");
		session.setAttribute("lastName", "Nagesh");

		logger.info("LOGGING IN...");

		return Response.status(200).build();
	}

	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	public void logoutAllDevices(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		SpringSessionBackedSessionRegistry sessionRegistry = new SpringSessionBackedSessionRegistry(sessionRepository);

		Collection<? extends ExpiringSession> usersSessions = sessions.findByIndexNameAndIndexValue(
				FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "sarathnagesh@gmail.com").values();
		usersSessions.forEach(session -> {
			String sessionId = session.getId();
			sessionRegistry.removeSessionInformation(sessionId);
			SessionInformation info = sessionRegistry.getSessionInformation(sessionId);
			info.expireNow();
			logger.info("sessionId: " + sessionId);
			// redisTemplate.delete("spring:session:sessions:expires:" +
			// sessionId);
			// redisTemplate.delete("spring:session:sessions:" + sessionId);
			redisTemplate.expire("spring:session:sessions:" + sessionId, 60, TimeUnit.SECONDS);
		});
	}

}
