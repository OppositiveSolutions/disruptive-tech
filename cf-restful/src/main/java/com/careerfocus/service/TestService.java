package com.careerfocus.service;

import com.careerfocus.dao.TestDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class TestService {

	 @Autowired
	    TestDAO testDAO;

	public List<Map<String,Object>> getAllTests(int userId, int type) {
		if (type == 1)
		return testDAO.getAllTests(userId);
		else 
		return testDAO.getAllExams(userId);
	}

	public List<Map<String,Object>> getTestCategoryDetails(int testId) {
		return testDAO.getCategoriesOfATest(testId);
	}

}
