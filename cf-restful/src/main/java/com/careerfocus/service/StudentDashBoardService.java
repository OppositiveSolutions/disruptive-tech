package com.careerfocus.service;

import com.careerfocus.dao.StudentDashBoardDAO;
import com.careerfocus.entity.Bundle;
import com.careerfocus.repository.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sarath on 04/06/17.
 */
@Service
public class StudentDashBoardService {

    @Autowired
    StudentDashBoardDAO dashBoardDAO;

	public String getSpeedTestSummary(int userId) {
		// TODO Auto-generated method stub
		return dashBoardDAO.getSpeedTestSummary(userId);
	}

	public String getSpeedTestSummary(int userId, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQPPurchasedList(int userId, int isCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSpeedTestGraphDetails(int userId, int examId) {
		// TODO Auto-generated method stub
		return null;
	}

}
