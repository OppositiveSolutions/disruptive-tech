package com.careerfocus.controller;

import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.service.StudentService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response addStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestBody AddStudentVO student) throws Exception {
		return studentService.addStudent(student);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
    public Response editStudent(@RequestBody AddStudentVO student) throws Exception {
        return Response.ok(studentService.editStudent(student)).build();
    }

	@RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}", method = RequestMethod.GET)
	public Response getStudent(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo) throws Exception {
		return Response.ok(studentService.getStudent(pageSize, pageNo)).build();
	}

	@RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}/search", method = RequestMethod.GET)
	public Response searchByName(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "key") String key, @PathVariable("pageSize") int pageSize,
			@PathVariable("pageNo") int pageNo) throws Exception {
		return Response.ok(studentService.findStudentsByName(key, pageSize, pageNo)).build();
	}

	@RequestMapping(value = "/{userId}/expiry", method = RequestMethod.PUT, consumes = { "text/plain" })
	public Response updateStudentExpiry(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userId") int userId, @RequestBody String expiryDate) throws Exception {
		studentService.updateStudentExpiry(userId, expiryDate);
		return Response.ok().build();
	}

}
