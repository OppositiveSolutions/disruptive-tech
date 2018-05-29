package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.service.ContactService;
import com.careerfocus.util.response.Response;

/**
 */

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	ContactService contactService;

	@RequestMapping(value = "/inquiry", method = RequestMethod.POST)
	public Response saveInquiry(HttpServletRequest request, @RequestParam("contact") String contact,
			@RequestParam("name") String name, @RequestParam("content") String content) throws Exception {
		return Response.ok(contactService.saveInquiry(contact, name, content)).build();
	}

}
