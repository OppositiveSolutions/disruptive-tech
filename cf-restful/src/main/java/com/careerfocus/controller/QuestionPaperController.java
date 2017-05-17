package com.careerfocus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.QuestionPaperCategory;
import com.careerfocus.entity.QuestionPaperSubCategory;
import com.careerfocus.model.request.QuestionVO;
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

	@RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}", method = RequestMethod.GET)
	public Response getAllQuestionPapers(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo) throws Exception {
		return Response.ok(qPaperService.getAllQuestionPapers(pageSize, pageNo)).build();
	}

	@RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}/search", method = RequestMethod.GET)
	public Response searchQuestionPaper(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo,
			@RequestParam(value = "key", required = false, defaultValue = "") String key) throws Exception {
		return Response.ok(qPaperService.searchQuestionPaper(key, pageSize, pageNo)).build();
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Response getAllQuestionPapersFullDetails(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return Response.ok(qPaperService.getAllQuestionPapersWithFullDetails()).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getQuestionPaper(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer id) throws Exception {
		return Response.ok(qPaperService.getQuestionPaper(id)).build();
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public Response saveQuestionPaperCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<QuestionPaperCategory> categoryList) throws Exception {
		return Response.ok(qPaperService.saveQuestionPaperCategories(categoryList)).build();
	}

	@RequestMapping(value = "/category", method = RequestMethod.PUT)
	public Response editQuestionPaperCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<QuestionPaperCategory> categoryList) throws Exception {
		return Response.ok(qPaperService.saveQuestionPaperCategories(categoryList)).build();
	}

	@RequestMapping(value = "/{questionPaperId}/category", method = RequestMethod.GET)
	public Response getQuestionPaperCategory(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("questionPaperId") Integer questionPaperId) throws Exception {
		return Response.ok(qPaperService.getQuestionPaperCategories(questionPaperId)).build();
	}

	@RequestMapping(value = "/sub-category", method = RequestMethod.POST)
	public Response saveQuestionPaperSubCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<QuestionPaperSubCategory> subCategoryList) throws Exception {
		return Response.ok(qPaperService.saveQuestionPaperSubCategory(subCategoryList)).build();
	}

	@RequestMapping(value = "/sub-category", method = RequestMethod.PUT)
	public Response editQuestionPaperSubCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<QuestionPaperSubCategory> subCategoryList) throws Exception {
		return Response.ok(qPaperService.saveQuestionPaperSubCategory(subCategoryList)).build();
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public Response saveQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<QuestionVO> qList) throws Exception {
		return Response.ok(qPaperService.saveQuestion(qList)).build();
	}

	@RequestMapping(value = "/{questionPaperId}/demo/{isDemo}", method = RequestMethod.POST)
	public Response setDemo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("questionPaperId") int questionPaperId, @PathVariable("isDemo") boolean isDemo)
			throws Exception {
		qPaperService.updateIsDemo(questionPaperId, isDemo);
		return Response.ok().build();
	}

}
