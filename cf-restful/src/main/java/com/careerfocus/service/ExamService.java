package com.careerfocus.service;

import com.careerfocus.dao.ExamDAO;
import com.careerfocus.dao.QuestionDAO;
import com.careerfocus.model.request.SaveQuestionVO;
import com.careerfocus.model.response.QuestionPopulateVO;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ExamService {

	@Autowired
    ExamDAO examDAO;
	
	@Autowired
    QuestionDAO questionDAO;

	public List<Map<String, String>> getExamCategoryDetails(int examId) {
		return examDAO.getExamCategoryDetails(examId);
	}

	public boolean startExam(int examId) {
		return examDAO.startExam(examId);
	}

	public int createExam(int userId, int qpId) {
		return examDAO.createExam(userId, qpId);
	}

	public List<Map<String, String>> getCategoryQStatusList(int examId, int categoryId) {
		return examDAO.getCategoryQStatusList(examId);
	}

	public QuestionPopulateVO getQuestion(int examId, int qId) {
		return questionDAO.getQuestion(examId, qId);
	}

	public int saveQuestion(SaveQuestionVO question) {
		return questionDAO.saveQuestion(question);
	}

	public boolean saveTime(int examId, int categoryId) {
		return examDAO.saveTime(examId, categoryId);
	}

}
