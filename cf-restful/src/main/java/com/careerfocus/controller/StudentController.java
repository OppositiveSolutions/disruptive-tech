package com.careerfocus.controller;

import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.service.StudentService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response addStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestBody AddStudentVO student, @RequestPart(value = "file", required = true) final MultipartFile image)
			throws Exception {
		return studentService.addStudent(student, image);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Response editStudent(@RequestBody AddStudentVO student, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(studentService.editStudent(student, userId)).build();
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

	@RequestMapping(value = "/{userId}/image", method = RequestMethod.GET)
	public byte[] getAchieverImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userId") int userId) throws Exception {
		return studentService.getUserImage(userId);
	}

	@RequestMapping(value = "/{userId}/image", method = RequestMethod.DELETE)
	public Response removeAchievers(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userId") int userId) throws Exception {
		return Response.ok(studentService.removeUserImage(userId)).build();
	}

	@RequestMapping(value = "/{userId}/image", method = RequestMethod.PUT)
	public Response saveAchieverImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userId") int userId, @RequestPart(value = "file", required = true) final MultipartFile image)
			throws Exception {
		return studentService.editUserImage(Integer.valueOf(userId), image);
	}

}
