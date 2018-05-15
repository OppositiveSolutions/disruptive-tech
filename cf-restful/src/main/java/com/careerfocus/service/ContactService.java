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

	public boolean saveInquiry(String contact, String name, String content) {
		return contactDAO.saveInquiry(contact, name, content);
	}

}
