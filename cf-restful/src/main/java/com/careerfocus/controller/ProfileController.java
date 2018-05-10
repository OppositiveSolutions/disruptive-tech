package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.service.ProfileService;
import com.careerfocus.util.response.Response;

/**
 */

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getStudentDetails(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(profileService.getStudentDetails(userId)).build();
	}

	@RequestMapping(value = "/password/change", method = RequestMethod.PUT, consumes = { "text/plain" })
	public Response changePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userId") int userId, @RequestParam("password") String password) throws Exception {
		return Response.ok(profileService.changePassword(userId, password)).build();
	}

	@RequestMapping(value = "/password/reset", method = RequestMethod.PUT, consumes = { "text/plain" })
	public Response resetPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("emailId") String emailId) throws Exception {
		return Response.ok(profileService.resetPassword(emailId)).build();
	}

}
