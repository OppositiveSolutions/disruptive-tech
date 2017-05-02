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
import com.careerfocus.service.StudentService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getVideoTutorial(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer id) throws Exception {
		return Response.ok(studentService.getStudent(id)).build();
	}

}
