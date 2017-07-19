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

	public String getAllExams(int userId) {
		// TODO Auto-generated method stub
		return testDAO.getAllExams(userId);
	}
	
	public String getAllTests(int userId) {
		// TODO Auto-generated method stub
		return testDAO.getAllTests(userId);
	}

}
