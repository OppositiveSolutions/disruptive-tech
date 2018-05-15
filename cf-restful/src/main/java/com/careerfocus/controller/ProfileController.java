package com.careerfocus.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.dao.ProfileDAO;
import com.careerfocus.service.ProfileService;
import com.careerfocus.util.response.Response;

/**
 */

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@Autowired
	ProfileDAO profileDAO;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getStudentDetails(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(profileService.getStudentDetails(userId)).build();
	}

	@RequestMapping(value = "/password/change", method = RequestMethod.GET)
	public Response changePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "uq_", defaultValue = "0", required = false) String uq_) throws Exception {
		int uId = 0;
		if (!uq_.equals("0")) {
			Map<String, Object> userMap = profileDAO.getUserIdAndPasswordFromUniqueString(uq_);
			if (userMap.get("user_id") != null) {
				uId = Integer.parseInt(userMap.get("user_id").toString());
				if (profileService.changePassword(uId, password))
					return Response.ok(profileDAO.deleteUniqueString(uId)).build();
			}
			return Response.ok(false).build();
		}
		if (userId != null)
			uId = Integer.parseInt(userId);
		return Response.ok(profileService.changePassword(uId, password)).build();
	}

	@RequestMapping(value = "/password/reset", method = RequestMethod.GET)
	public Response resetPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("emailId") String emailId) throws Exception {
		return Response.ok(profileService.resetPassword(emailId)).build();
	}

}
