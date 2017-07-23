package com.careerfocus.controller;

import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.model.request.SaveQuestionVO;
import com.careerfocus.service.ProfileService;
import com.careerfocus.util.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@RequestMapping(value = "/myprofile/{userId}", method = RequestMethod.GET)
	public Response getMyProfile(@PathVariable("userId") int userId) throws Exception {
		return Response.ok(profileService.getDetailsForMyProfile(userId)).build();
	}

	@RequestMapping(value = "/myprofile/edit", method = RequestMethod.PUT)
	public Response editMyProfile(@RequestBody AddStudentVO student) throws Exception {
		return Response.ok(profileService.editMyProfile(student)).build();
	}

}
