package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.service.QuestionPaperService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/question-paper")
public class QuestionPaperController {
	
	@Autowired
	QuestionPaperService qPaperService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveQuestionPaper(HttpServletRequest request, HttpServletResponse response,
			@RequestBody QuestionPaper qPaper) throws Exception {
		return Response.ok(qPaperService.addQuestionPaper(qPaper)).build();
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Response editQuestionPaper(HttpServletRequest request, HttpServletResponse response,
			@RequestBody QuestionPaper qPaper) throws Exception {
		return Response.ok(qPaperService.addQuestionPaper(qPaper)).build();
	}
	
	
	
}
