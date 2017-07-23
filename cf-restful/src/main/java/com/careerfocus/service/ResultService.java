package com.careerfocus.service;

import com.careerfocus.dao.ResultDAO;
import com.careerfocus.entity.Bundle;
import com.careerfocus.repository.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ResultService {

    @Autowired
    ResultDAO resultDAO;

	public String getScoreCard(int examId, int isDemo) {
		// TODO Auto-generated method stub
		return resultDAO.getScoreCard(examId);
	}

	public String getCategoryScore(int examId, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCategoryScoreGraph(int examId, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCategoryTime(int examId, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTopTenScore(int userId, int examId) {
		// TODO Auto-generated method stub
		return null;
	}

}
