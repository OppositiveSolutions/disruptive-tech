package com.careerfocus.controller;

import com.careerfocus.service.ResultService;
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
@RequestMapping("/result")
public class ResultController {

	@Autowired
    ResultService resultService;

    @RequestMapping(value = "/scorecard/{examId}", method = RequestMethod.GET)
    public Response getScoreCard(@PathVariable("examId") int examId)
            throws Exception {
        return Response.ok(resultService.getScoreCard(examId)).build();
    }

}
