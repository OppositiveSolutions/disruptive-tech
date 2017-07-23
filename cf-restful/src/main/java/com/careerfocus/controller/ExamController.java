package com.careerfocus.controller;

import com.careerfocus.model.request.SaveQuestionVO;
import com.careerfocus.service.ExamService;
import com.careerfocus.util.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamService examService;

    @RequestMapping(value = "/categories/{examId}", method = RequestMethod.GET)
    public Response getCategorieList(@PathVariable("examId") int examId)
            throws Exception {
        return Response.ok(examService.getExamCategoryDetails(examId)).build();
    }
    
    @RequestMapping(value = "{examId}/startexam/{language}", method = RequestMethod.POST)
    public Response startExam(@PathVariable("examId") int examId, @PathVariable("language") int language) throws Exception {
        return Response.ok(examService.startExam(examId, language)).build();
    }
    
    @RequestMapping(value = "/{testId}/create/{isDemo}", method = RequestMethod.POST)
    public Response createExam(@PathVariable("testId") int testId, @PathVariable("isDemo") int isDemo) throws Exception {
        return Response.ok(examService.createExam(testId, isDemo)).build();
    }
    
    @RequestMapping(value = "/{examId}/category/{categoryId}/qstatus", method = RequestMethod.GET)
    public Response getCategoryQStatusList(@PathVariable("examId") int examId, @PathVariable("categoryId") int categoryId)
            throws Exception {
        return Response.ok(examService.getCategoryQStatusList(examId, categoryId)).build();
    }
    
    @RequestMapping(value = "/{examId}/question/{qId}", method = RequestMethod.GET)
    public Response getQuestion(@PathVariable("examId") int examId, @PathVariable("qId") int qId)
            throws Exception {
        return Response.ok(examService.getQuestion(examId, qId)).build();
    }
    
    @RequestMapping(value = "/save/question", method = RequestMethod.POST)
    public Response saveQuestion(HttpServletRequest request, HttpServletResponse response,
            @RequestBody SaveQuestionVO question) throws Exception {
        return Response.ok(examService.saveQuestion(question)).build();
    }
    
    @RequestMapping(value = "/save/{examId}/time/{categoryId}", method = RequestMethod.POST)
    public Response saveTime(@PathVariable("examId") int examId, @PathVariable("categoryId") int categoryId) throws Exception {
        return Response.ok(examService.saveTime(examId, categoryId)).build();
    }

}
