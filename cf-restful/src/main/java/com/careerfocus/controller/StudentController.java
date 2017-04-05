package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.entity.Student;
import com.careerfocus.service.StudentService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	StudentService studentService;

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

}
