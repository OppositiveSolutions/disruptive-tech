package com.careerfocus.service;

import com.careerfocus.dao.ExamDAO;
import com.careerfocus.dao.QuestionDAO;
import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.model.request.SaveQuestionVO;
import com.careerfocus.model.response.QuestionPopulateVO;
import com.careerfocus.repository.QuestionPaperRepository;

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

	@Autowired
	QuestionPaperRepository qPaperRepository;

	public Map<String, Object> getExamCategoryDetails(int examId) {
		return examDAO.getExamCategoryDetails(examId);
	}

	public boolean startExam(int examId, int language) {
		if (examDAO.initializeExam(examId, language))
			return examDAO.initializeExamCategoryTime(examId);
		else
			return false;
	}

	public int createExam(int testId, int isDemo) {
		int examId = examDAO.createExam(testId, isDemo);
		List<Map<String, Object>> categoryBasedQList = examDAO.getNoOfQsPerCategory(examId);
		for (Map<String, Object> m : categoryBasedQList) {
			int categoryId = (Integer) m.get("category_id");
			examDAO.createExamCategoryTime(examId, categoryId);
			examDAO.createExamCategoryMark(examId, categoryId);
		}
		return examId;
	}

	public List<Map<String, Object>> getCategoryQStatusList(int examId, int categoryId) {
		return examDAO.getCategoriesQStatusList(examId, categoryId);
	}

	public QuestionPopulateVO getQuestion(int examId, int qNo) {
		return questionDAO.getQuestion(examId, qNo);
	}

	public boolean saveExam(SaveQuestionVO[] questions, int examId, int userId) {
		boolean status = false;
		questionDAO.clearExamQuestion(examId);
		for (SaveQuestionVO q : questions) {
			status = questionDAO.saveQuestion(q, examId);
		}
		if (status) {
			status = false;
			if (examDAO.updateExam(examId, userId))
				if (examDAO.updateCategoryMark(examId))
					if (examDAO.updateExamResult(examId))
						status = true;
		}
		return status;
	}

	public boolean saveTime(int examId, int categoryId) {
		return examDAO.saveTime(examId, categoryId);
	}

	public QuestionPaper getExamWithFullQuestionDetails(int examId) {
		return qPaperRepository.findOne(examDAO.getQuestionPaperIdFromExamId(examId));
	}

}
