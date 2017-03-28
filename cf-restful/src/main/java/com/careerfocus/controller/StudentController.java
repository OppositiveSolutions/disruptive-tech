package com.careerfocus.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.model.Student;
import com.careerfocus.response.Response;
import com.careerfocus.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName().trim());

	@Autowired
	StudentService studentService;

	// @Autowired
	// Scheduler scheduler;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		logger.setLevel(Level.DEBUG);
		logger.info("THIS IS A LOG INFO LOGGING");

		ArrayList<Student> list = new ArrayList<Student>() {

			private static final long serialVersionUID = 1L;

			{
				add(new Student(1, "john", 101));
				add(new Student(2, "will", 102));
				add(new Student(3, "jack", 103));
				add(new Student(4, "bill", 104));
			}
		};
		
		Response r = Response.status(true).data(list).build();
		
		logger.info("### RESPONSE: " + r.toJsonString());
		
		return r;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Response login(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		session.setAttribute("role", 1);
		session.setAttribute("firstName", "Sarath");
		session.setAttribute("lastName", "Nagesh");
		
		logger.info("LOGGING IN...");

		Response r = Response.status(true).build();
		
		logger.info("### RESPONSE: " + r.toJsonString());
		
		return r;
	}
	
	

}
