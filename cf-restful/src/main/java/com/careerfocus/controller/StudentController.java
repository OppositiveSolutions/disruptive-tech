package com.careerfocus.controller;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.entity.Student;
import com.careerfocus.response.Response;
import com.careerfocus.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	StudentService studentService;

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	// @Autowired
	// Scheduler scheduler;

	@SuppressWarnings("rawtypes")
	@Autowired
	private FindByIndexNameSessionRepository sessionRepository;

	@Autowired
	FindByIndexNameSessionRepository<? extends ExpiringSession> sessions;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// logger.setLevel(Level.DEBUG);
		logger.info("THIS IS A LOG INFO LOGGING");

		// ArrayList<Student> list = new ArrayList<Student>() {
		//
		// private static final long serialVersionUID = 1L;
		//
		// {
		// add(new Student(1, "john", 101));
		// add(new Student(2, "will", 102));
		// add(new Student(3, "jack", 103));
		// add(new Student(4, "bill", 104));
		// }
		// };
		//
		// Response r = Response.status(200).data(list).build();

		// logger.info("### RESPONSE: " + r.toJsonString());

		return Response.ok(studentService.getAllStudents()).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id)
			throws Exception {
		return Response.ok(studentService.getStudent(id)).build();
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveStudent(HttpServletRequest request, HttpServletResponse response, @RequestBody Student student)
			throws Exception {
		return Response.ok(studentService.addNewStudent(student)).build();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Response login(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		session.setMaxInactiveInterval(-1);

		session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "sarathnagesh@gmail.com");
		session.setAttribute("role", 1);
		session.setAttribute("firstName", "Sarath");
		session.setAttribute("lastName", "Nagesh");

		logger.info("LOGGING IN...");

		Response r = Response.status(200).build();

		logger.info("### RESPONSE: " + r.toJsonString());

		return r;
	}

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public Response test(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id)
			throws Exception {

//		SpringSessionBackedSessionRegistry sessionRegistry = new SpringSessionBackedSessionRegistry(sessionRepository);

		Collection<? extends ExpiringSession> usersSessions = sessions.findByIndexNameAndIndexValue(
				FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "sarathnagesh@gmail.com").values();

		// usersSessions.forEach((temp) -> {
		//
		// String sessionId = temp.getId();
		// // sessionRegistry.removeSessionInformation(sessionId);
		// SessionInformation info =
		// sessionRegistry.getSessionInformation(sessionId);
		// info.expireNow();
		// });

		// List<SessionInformation> infos =
		// sessionRegistry.getAllSessions("sarathnagesh@gmail.com", false);
		// Enumeration<String> e = usersSessions.getAttributeNames();
		// while (e.hasMoreElements()) {
		// String name = (String) e.nextElement();
		// log.info(name + ": " + session.getAttribute(name));
		// }

		// return
		// Response.ok(redisTemplate.opsForValue().get("user:name")).build();
		// return null;

		return Response.ok(usersSessions).build();

	}

	@RequestMapping(value = "/logoutalldevices", method = RequestMethod.GET)
	public Response test(HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		SpringSessionBackedSessionRegistry sessionRegistry = new SpringSessionBackedSessionRegistry(sessionRepository);

		Collection<? extends ExpiringSession> usersSessions = sessions.findByIndexNameAndIndexValue(
				FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "sarathnagesh@gmail.com").values();
		usersSessions.forEach((temp) -> {
			String sessionId = temp.getId();
			// sessionRegistry.removeSessionInformation(sessionId);
			SessionInformation info = sessionRegistry.getSessionInformation(sessionId);
			info.expireNow();
			logger.info("sessionId: " + sessionId);
			// redisTemplate.delete("spring:session:sessions:expires:" +
			// sessionId);
			// redisTemplate.delete("spring:session:sessions:" + sessionId);
			redisTemplate.expire("spring:session:sessions:" + sessionId, 60, TimeUnit.SECONDS);
		});

		return Response.ok().build();
	}

}
