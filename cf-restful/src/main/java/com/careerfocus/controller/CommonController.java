package com.careerfocus.controller;

import com.careerfocus.service.CommonService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/coachingtypes", method = RequestMethod.GET)
	public Response getCoachingTypes() throws Exception {
		return Response.ok(commonService.getCoachingTypes()).build();
	}
}
