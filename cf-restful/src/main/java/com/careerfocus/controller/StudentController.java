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

import com.careerfocus.entity.Temp0;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.service.StudentService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response addStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestBody AddStudentVO student) throws Exception {
		return studentService.addStudent(student);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return Response.ok(studentService.getStudent(0, 0)).build();
	}

}
