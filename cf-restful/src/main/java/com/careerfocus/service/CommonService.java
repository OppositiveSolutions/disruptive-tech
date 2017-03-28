package com.careerfocus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.CommonDAO;

@Service
public class CommonService {

	@Autowired
	CommonDAO commonDAO;

	public String setLogLevel(int level) throws Exception {
		return commonDAO.setLogLevel(level);
	}
}
