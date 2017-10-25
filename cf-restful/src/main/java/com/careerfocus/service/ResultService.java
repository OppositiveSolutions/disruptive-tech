package com.careerfocus.service;

import com.careerfocus.dao.ExamDAO;
import com.careerfocus.dao.ResultDAO;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ResultService {

    @Autowired
    ResultDAO resultDAO;
    
    @Autowired
    ExamDAO examDAO;

	public List<Map<String,Object>> getScoreCard(int userId) {
		return resultDAO.getScoreCard(userId);
	}

	public List<Map<String,Object>> getExamCategoriesScoreList(int examId) {
		return resultDAO.getExamCategoriesScoreList(examId);
	}

	public List<Map<String,Object>> getExamCategoriesScoreGraph(int examId) {
		return resultDAO.getExamCategoriesScoreGraph(examId);
	}

	public List<Map<String,Object>> getExamCategoryTimeList(int examId) {
		return examDAO.getTotalTimeTakenPerCategory(examId);
	}

	public List<Map<String,Object>> getTopTenScore(int examId) {
		return resultDAO.getTopTenScore(examId);
	}

	public String getExamCategoriesTimeGraph(int examId) {
		// TODO Auto-generated method stub
		return null;
	}

}
