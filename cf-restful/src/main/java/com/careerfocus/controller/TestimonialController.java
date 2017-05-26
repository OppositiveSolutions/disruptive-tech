package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.careerfocus.entity.Testimonial;
import com.careerfocus.service.TestimonialService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/testimonial")
public class TestimonialController {

	@Autowired
	TestimonialService service;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveTesticle(HttpServletRequest request, @RequestBody Testimonial testicle)
			throws Exception {
		return Response.ok(service.saveTesticles(request, testicle)).build();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getAllTesticles(HttpServletRequest request) throws Exception {
		return Response.ok(service.getAllTesticles()).build();
	}
	
	@RequestMapping(value = "/self", method = RequestMethod.GET)
	public Response getAllTesticlesOfUser(HttpServletRequest request) throws Exception {
		return Response.ok(service.getAllTestimonialsOfUser(request)).build();
	}

}
