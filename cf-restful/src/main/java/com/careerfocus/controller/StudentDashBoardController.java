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

    @RequestMapping(value = "/speedtest/{userId}/summary/{type}", method = RequestMethod.GET)
    public Response getSpeedTestSummary(@PathVariable("userId") int userId, @PathVariable("type") int type)
            throws Exception {
        return Response.ok(dashBoardService.getSpeedTestSummary(userId, type)).build();
    }
    
    @RequestMapping(value = "/qpbundle/{userId}/purchase/{isCurrent}", method = RequestMethod.GET)
    public Response getQPPurchasedList(@PathVariable("userId") int userId, @PathVariable("isCurrent") int isCurrent)
            throws Exception {
        return Response.ok(dashBoardService.getQPPurchasedList(userId, isCurrent)).build();
    }
    
    @RequestMapping(value = "/speedtest/{userId}/graph/{examId}", method = RequestMethod.GET)
    public Response getSpeedTestGraphDetails(@PathVariable("userId") int userId, @PathVariable("examId") int examId)
            throws Exception {
        return Response.ok(dashBoardService.getSpeedTestGraphDetails(userId, examId)).build();
    }

}
