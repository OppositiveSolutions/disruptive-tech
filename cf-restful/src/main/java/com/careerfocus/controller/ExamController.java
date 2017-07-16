package com.careerfocus.controller;

import com.careerfocus.service.ExamService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
        return Response.ok(examService.getCategorieList(examId)).build();
    }

}
