package com.careerfocus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.service.CommonService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/log4j/{level}", method = RequestMethod.GET)
	public Response setLogLevel(@PathVariable("level") int level) throws Exception {
		return Response.ok(commonService.setLogLevel(level)).build();
	}

	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public Response getStates() throws Exception {
		return Response.ok(commonService.getStates()).build();
	}

	@RequestMapping(value = "/email/is-valid", method = RequestMethod.GET)
	public Response checkEmailExists(@RequestParam("emailId") String emailId) throws Exception {
		return commonService.checkEmailExists(emailId);
	}
}
