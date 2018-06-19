package com.careerfocus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.dao.ProfileDAO;
import com.careerfocus.model.request.AddStudentVO;
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
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Response editStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestBody AddStudentVO student) throws Exception {
		return Response.ok(profileService.editProfile(student)).build();
	}

	@RequestMapping(value = "/password/change", method = RequestMethod.POST)
	public Map<String, Object> changePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestBody Map<String, Object> passwordMap,
			@RequestParam(value = "uq_", defaultValue = "0", required = false) String uq_) throws Exception {
		int uId = 0;
		System.out.println(passwordMap);
		String password = "0";
		if (passwordMap != null) {
			password = passwordMap.get("password").toString();
		}
		Map<String, Object> returnMap = new HashMap<>();
		if (!uq_.equals("0")) {
			Map<String, Object> userMap = profileDAO.getUserIdAndPasswordFromUniqueString(uq_);
			if (userMap.get("user_id") != null) {
				uId = Integer.parseInt(userMap.get("user_id").toString());
				returnMap = profileService.changePassword(uId, password);
				if ((boolean) returnMap.get("status"))
					if (profileDAO.deleteUniqueString(uId))
						return returnMap;
			}
			returnMap.put("status", false);
			returnMap.put("message", "The  password reset request expired. Please start over again.");
			return returnMap;
		}
		if (userId != null)
			uId = Integer.parseInt(userId);
		return profileService.changePassword(uId, password);
	}

	@RequestMapping(value = "/password/reset", method = RequestMethod.GET)
	public Map<String, Object> resetPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("emailId") String emailId) throws Exception {
		return profileService.resetPassword(emailId);
	}

}
