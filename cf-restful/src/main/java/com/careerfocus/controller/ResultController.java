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

    @RequestMapping(value = "/{examId}/score/{isDemo}", method = RequestMethod.GET)
    public Response getScoreCard(@PathVariable("examId") int examId, @PathVariable("isDemo") int isDemo)
            throws Exception {
        return Response.ok(resultService.getScoreCard(examId, isDemo)).build();
    }
    
    @RequestMapping(value = "/{examId}/score/{categoryId}/category", method = RequestMethod.GET)
    public Response getCategoryScore(@PathVariable("examId") int examId, @PathVariable("categoryId") int categoryId)
            throws Exception {
        return Response.ok(resultService.getCategoryScore(examId, categoryId)).build();
    }
    
    @RequestMapping(value = "/{examId}/scoregraph/{categoryId}/category", method = RequestMethod.GET)
    public Response getCategoryScoreGraph(@PathVariable("examId") int examId, @PathVariable("categoryId") int categoryId)
            throws Exception {
        return Response.ok(resultService.getCategoryScoreGraph(examId, categoryId)).build();
    }
    
    @RequestMapping(value = "/{examId}/time/{categoryId}/category", method = RequestMethod.GET)
    public Response getCategoryTime(@PathVariable("examId") int examId, @PathVariable("categoryId") int categoryId)
            throws Exception {
        return Response.ok(resultService.getCategoryTime(examId, categoryId)).build();
    }
    
    @RequestMapping(value = "/{examId}/timegraph/{categoryId}/category", method = RequestMethod.GET)
    public Response getCategoryTimeGraph(@PathVariable("examId") int examId, @PathVariable("categoryId") int categoryId)
            throws Exception {
        return Response.ok(resultService.getCategoryScore(examId, categoryId)).build();
    }
    
    @RequestMapping(value = "/{userId}/topten/{examId}", method = RequestMethod.GET)
    public Response getTopTenScore(@PathVariable("examId") int examId, @PathVariable("userId") int userId)
            throws Exception {
        return Response.ok(resultService.getTopTenScore(userId, examId)).build();
    }
    
    @RequestMapping(value = "/{examId}/answers", method = RequestMethod.GET)
    public Response getCompleteQPWithAnswers(@PathVariable("examId") int examId, @PathVariable("categoryId") int categoryId)
            throws Exception {
    	//chungBro
        return Response.ok(null).build();
    }

}
