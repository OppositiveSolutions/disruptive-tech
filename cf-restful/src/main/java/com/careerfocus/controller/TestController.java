package com.careerfocus.controller;

import com.careerfocus.service.TestService;
import com.careerfocus.util.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestService testService;

	@RequestMapping(value = "/all/{type}", method = RequestMethod.GET)
	public Response getAllTests(HttpServletRequest request, @PathVariable("type") int type) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(testService.getAllTests(userId, type)).build();
	}

	@RequestMapping(value = "/categories/{testId}", method = RequestMethod.GET)
	public Response getCategorieList(@PathVariable("testId") int testId) throws Exception {
		return Response.ok(testService.getTestCategoryDetails(testId)).build();
	}

}
