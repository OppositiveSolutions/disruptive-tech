package com.careerfocus.service;

import com.careerfocus.dao.ContactDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ContactService {

	@Autowired
	ContactDAO contactDAO;

	public boolean saveInquiry(String emailId, String phone, String content) {
		return contactDAO.saveInquiry(emailId, phone, content);
	}

}
