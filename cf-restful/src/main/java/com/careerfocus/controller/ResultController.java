package com.careerfocus.controller;

import com.careerfocus.service.ResultService;
import com.careerfocus.util.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/scorecard", method = RequestMethod.GET)
	public Response getScoreCard(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(resultService.getScoreCard(userId)).build();
	}

	@RequestMapping(value = "/{examId}/score", method = RequestMethod.GET)
	public Response getCategoryScore(@PathVariable("examId") int examId) throws Exception {
		return Response.ok(resultService.getExamCategoriesScoreList(examId)).build();
	}

	@RequestMapping(value = "/{examId}/scoregraph", method = RequestMethod.GET)
	public Response getCategoryScoreGraph(@PathVariable("examId") int examId) throws Exception {
		return Response.ok(resultService.getExamCategoriesScoreGraph(examId)).build();
	}

	@RequestMapping(value = "/{examId}/timepercategory", method = RequestMethod.GET)
	public Response getCategoryTime(@PathVariable("examId") int examId) throws Exception {
		return Response.ok(resultService.getExamCategoryTimeList(examId)).build();
	}

	@RequestMapping(value = "/{examId}/timegraph", method = RequestMethod.GET)
	public Response getCategoryTimeGraph(@PathVariable("examId") int examId) throws Exception {
		return Response.ok(resultService.getExamCategoriesTimeGraph(examId)).build();
	}

	@RequestMapping(value = "/{examId}/topten", method = RequestMethod.GET)
	public Response getTopTenScore(@PathVariable("examId") int examId) throws Exception {
		return Response.ok(resultService.getTopTenScore(examId)).build();
	}

	@RequestMapping(value = "/{examId}/answers", method = RequestMethod.GET)
	public Response getCompleteQPWithAnswers(@PathVariable("examId") int examId,
			@PathVariable("categoryId") int categoryId) throws Exception {
		// chungBro
		return Response.ok(null).build();
	}

	@RequestMapping(value = "/accuracy", method = RequestMethod.GET)
	public Response getUserAccuracy(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(resultService.getUserAccuracy(userId)).build();
	}

	@RequestMapping(value = "/avgtime", method = RequestMethod.GET)
	public Response getUserAverageTimePerQuestion(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(resultService.getUserAverageTimePerQuestion(userId)).build();
	}

	@RequestMapping(value = "/examcount", method = RequestMethod.GET)
	public Response getUserExamAndTestCount(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		return Response.ok(resultService.getUserExamAndTestCount(userId)).build();
	}

}
