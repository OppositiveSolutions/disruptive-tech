package com.careerfocus.service;

import com.careerfocus.dao.ExamDAO;
import com.careerfocus.entity.Bundle;
import com.careerfocus.repository.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ExamService {

	@Autowired
    ExamDAO examDAO;

	public String getCategorieList(int examId) {
		// TODO Auto-generated method stub
		return examDAO.getCategorieList(examId);
	}

}
