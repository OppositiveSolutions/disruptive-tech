package com.careerfocus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.response.Response;
import com.careerfocus.service.CommonService;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	CommonService commonService;
	

	@RequestMapping(value = "/log4j/{level}", method = RequestMethod.GET)
	public Response setLogLevel(@PathVariable("level") int level) throws Exception {
		return Response.ok(commonService.setLogLevel(level)).build();
	}
}
