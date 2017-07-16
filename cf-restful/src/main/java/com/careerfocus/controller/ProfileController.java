package com.careerfocus.controller;

import com.careerfocus.service.ProfileService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
    ProfileService profileService;

    @RequestMapping(value = "/myprofile/{userId}", method = RequestMethod.GET)
    public Response getDetailsForMyProfile(@PathVariable("userId") int userId)
            throws Exception {
        return Response.ok(profileService.getDetailsForMyProfile(userId)).build();
    }

}
