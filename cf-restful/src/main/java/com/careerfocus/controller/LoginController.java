package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.service.LoginService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("")
public class LoginController {
	
	@Autowired 
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Response login(@FormParam("username") String username,
			@FormParam("password") String password, HttpServletRequest request) throws Exception {
		return Response.ok(loginService.performLogin(username, password, request)).build();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody Response getLoginedInUser(HttpServletRequest request) throws Exception {
		return Response.ok(loginService.getUserDetails(request)).build();
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public Response logout(HttpServletRequest request) throws Exception {
		loginService.logout(request);
		return Response.ok().build();
	}
	
	@RequestMapping(value = "/logout-all-devices", method = RequestMethod.GET)
	public Response logoutAllDevices(HttpServletRequest request) throws Exception {
		loginService.logoutAllDevices(request);
		return Response.ok().build();
	}

}
