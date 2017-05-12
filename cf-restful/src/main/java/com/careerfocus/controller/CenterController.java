package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.entity.Center;
import com.careerfocus.service.CenterService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/center")
public class CenterController {

	@Autowired
	CenterService service;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveCenter(HttpServletRequest request, HttpServletResponse response, @RequestBody Center center)
			throws Exception {
		return service.saveCenter(center);
	}

	@RequestMapping(value = "/{centerId}", method = RequestMethod.GET)
	public Response getCenter(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("centerId") int centerId) throws Exception {
		return Response.ok(service.getCenter(centerId)).build();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getAllCenters(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return Response.ok(service.getAllCenters()).build();
	}

	@RequestMapping(value = "/{centerId}", method = RequestMethod.DELETE)
	public Response deleteCenter(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("centerId") int centerId) throws Exception {
		service.deleteCenter(centerId);
		return Response.ok().build();
	}
	
	@RequestMapping(value = "/{centerId}/students", method = RequestMethod.GET)
	public Response getAllStudentsInCenter(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("centerId") int centerId) throws Exception {
		return Response.ok(service.getAllStudentsInCenter(centerId)).build();
	}

}
