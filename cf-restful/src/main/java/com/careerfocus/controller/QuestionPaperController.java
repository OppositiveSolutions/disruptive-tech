package com.careerfocus.controller;

import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.QuestionPaperCategory;
import com.careerfocus.entity.QuestionPaperSubCategory;
import com.careerfocus.model.request.QuestionVO;
import com.careerfocus.service.QuestionPaperService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/question-paper")
public class QuestionPaperController {

	@Autowired
	private QuestionPaperService qPaperService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveQuestionPaper(@RequestBody QuestionPaper qPaper) throws Exception {
		return Response.ok(qPaperService.addQuestionPaper(qPaper)).build();
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Response editQuestionPaper(@RequestBody QuestionPaper qPaper) throws Exception {
		return Response.ok(qPaperService.editQuestionPaper(qPaper)).build();
	}

	@RequestMapping(value = "/{questionPaperId}", method = RequestMethod.DELETE)
	public Response removeQP(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("questionPaperId") int questionPaperId) throws Exception {
		return Response.ok(qPaperService.removeQP(questionPaperId)).build();
	}

	@RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}", method = RequestMethod.GET)
	public Response getAllQuestionPapers(@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo)
			throws Exception {
		return Response.ok(qPaperService.getAllQuestionPapers(pageSize, pageNo)).build();
	}

	@RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}/search", method = RequestMethod.GET)
	public Response searchQuestionPaper(@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo,
			@RequestParam(value = "key", required = false, defaultValue = "") String key) throws Exception {
		return Response.ok(qPaperService.searchQuestionPaper(key, pageSize, pageNo)).build();
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Response getAllQuestionPapersFullDetails(@RequestParam(value = "coachingType", required = false, defaultValue = "0") String coachingType) throws Exception {
		return Response.ok(qPaperService.getAllQuestionPapersWithFullDetails(coachingType)).build();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Response getAllQuestionPapers(
			@RequestParam(value = "coachingType", required = false, defaultValue = "0") int coachingType)
			throws Exception {
		return Response.ok(qPaperService.getAllQuestionPapers(coachingType)).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getQuestionPaper(@PathVariable("id") Integer id) throws Exception {
		return Response.ok(qPaperService.getQuestionPaper(id)).build();
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public Response saveQuestionPaperCategory(@RequestBody List<QuestionPaperCategory> categoryList) throws Exception {
		return Response.ok(qPaperService.saveQuestionPaperCategories(categoryList)).build();
	}

	@RequestMapping(value = "/{questionPaperId}/category", method = RequestMethod.PUT)
	public Response editQuestionPaperCategory(@RequestBody List<QuestionPaperCategory> categoryList,
			@PathVariable int questionPaperId) throws Exception {
		return Response.ok(qPaperService.editQuestionPaperCategories(categoryList, questionPaperId)).build();
	}

	@RequestMapping(value = "/{questionPaperId}/category", method = RequestMethod.GET)
	public Response getQuestionPaperCategory(@PathVariable("questionPaperId") Integer questionPaperId)
			throws Exception {
		return Response.ok(qPaperService.getQuestionPaperCategories(questionPaperId)).build();
	}

	@RequestMapping(value = "/category/{categoryId}/sub-category", method = RequestMethod.GET)
	public Response getQuestionPaperSubCategory(@PathVariable("categoryId") int categoryId) throws Exception {
		return Response.ok(qPaperService.getQuestionPaperSubCategories(categoryId)).build();
	}

	@RequestMapping(value = "/sub-category", method = RequestMethod.POST)
	public Response saveQuestionPaperSubCategory(@RequestBody List<QuestionPaperSubCategory> subCategoryList)
			throws Exception {
		return Response.ok(qPaperService.saveQuestionPaperSubCategory(subCategoryList)).build();
	}

	@RequestMapping(value = "/sub-category", method = RequestMethod.PUT)
	public Response editQuestionPaperSubCategory(@RequestBody List<QuestionPaperSubCategory> subCategoryList)
			throws Exception {
		return Response.ok(qPaperService.saveQuestionPaperSubCategory(subCategoryList)).build();
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public Response saveQuestion(@RequestBody List<QuestionVO> qList) throws Exception {
		return Response.ok(qPaperService.saveQuestion(qList)).build();
	}

	@RequestMapping(value = "/question", method = RequestMethod.PUT)
	public Response editQuestion(@RequestBody List<QuestionVO> qList) throws Exception {
		return Response.ok(qPaperService.editQuestion(qList)).build();
	}

	@RequestMapping(value = "/sub-category/{subCategoryId}/questions", method = RequestMethod.GET)
	public Response getQuestions(@PathVariable("subCategoryId") int subCategoryId) throws Exception {
		return Response.ok(qPaperService.getQuestions(subCategoryId)).build();
	}

	@RequestMapping(value = "/{questionPaperId}/demo/{isDemo}", method = RequestMethod.POST)
	public Response setDemo(@PathVariable("questionPaperId") int questionPaperId,
			@PathVariable("isDemo") boolean isDemo) throws Exception {
		qPaperService.updateIsDemo(questionPaperId, isDemo);
		return Response.ok().build();
	}

	@RequestMapping(value = "/complete-status/{questionPaperId}", method = RequestMethod.GET)
	public Response getQuestionPaperCompletionStatus(@PathVariable("questionPaperId") Integer questionPaperId)
			throws Exception {
		return Response.ok(qPaperService.isQuestionPaperComplete(questionPaperId)).build();
	}

}
