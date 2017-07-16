package com.careerfocus.controller;

import com.careerfocus.service.StudentDashBoardService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */

@RestController
@RequestMapping("/dashboard")
public class StudentDashBoardController {

	@Autowired
    StudentDashBoardService dashBoardService;

    @RequestMapping(value = "/speedtest/summary/{userId}", method = RequestMethod.GET)
    public Response getSpeedTestSummary(@PathVariable("userId") int userId)
            throws Exception {
        return Response.ok(dashBoardService.getSpeedTestSummary(userId)).build();
    }

}
