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

	public Map<String, Object> getExamCategoryDetails(int examId) {
		return examDAO.getExamCategoryDetails(examId);
	}

	public boolean startExam(int examId, int language) {
		return examDAO.startExam(examId, language);
	}

	public int createExam(int testId, int isDemo) {
		int examId = examDAO.createExam(testId, isDemo);
		if (examDAO.createExamQuestions(examId) > 0) {
			List<Map<String, Object>> categoryBasedQList = examDAO.getNoOfQsPerCategory(examId);
			for (Map<String, Object> m : categoryBasedQList) {
				int categoryId = (Integer) m.get("category_id");
				examDAO.createExamCategoryTime(examId, categoryId);
			}
		}
		return examId;
	}

	public List<Map<String, Object>> getCategoryQStatusList(int examId, int categoryId) {
		return examDAO.getCategoriesQStatusList(examId, categoryId);
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
