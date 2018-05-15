package com.careerfocus.controller;

import com.careerfocus.service.TestimonialService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/testimonial")
public class TestimonialController {

	@Autowired
	TestimonialService service;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveTestimonials(HttpServletRequest request, @RequestPart String testimonial,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		return Response.ok(service.saveTestimonials(request, testimonial, image)).build();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getAllTestimonials(HttpServletRequest request,HttpServletRequest response) throws Exception {
		response.setAttribute("Allow-Control-Allow-Origin", "*");
		return Response.ok(service.getAllTestimonials()).build();
		
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public Response deleteTestimonial(@PathVariable("testimonialId") Integer testimonialId) throws Exception {
		return Response.ok(service.deleteTestimonial(testimonialId)).build();
	}

	@RequestMapping(value = "/self", method = RequestMethod.GET)
	public Response getAllTestimonialsOfUser(HttpServletRequest request) throws Exception {
		return Response.ok(service.getAllTestimonialsOfUser(request)).build();
	}

}
