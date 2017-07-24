package com.careerfocus.service;

import com.careerfocus.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class TestService {

	 @Autowired
	    TestDAO testDAO;

	public String getAllTests(int userId, int type) {
		if (type == 1)
		return testDAO.getAllTests(userId);
		else 
		return testDAO.getAllExams(userId);
	}

}
