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

	public String getScoreCard(int examId) {
		// TODO Auto-generated method stub
		return resultDAO.getScoreCard(examId);
	}

}
