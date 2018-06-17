package com.careerfocus.service;

import com.careerfocus.dao.ProfileDAO;
import com.careerfocus.entity.Student;
import com.careerfocus.repository.StudentRepository;
import com.careerfocus.repository.UserRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ProfileService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfileDAO profileDAO;

	public Student getStudentDetails(int userId) {

		return studentRepository.findOne(userId);
	}

	public Map<String, Object> changePassword(int userId, String password) {
		return profileDAO.changePassword(userId, password);
	}

	public Map<String, Object> resetPassword(String emailId) {
//		String password = PasswordGenerator.generateSixDigitPassword();
		return profileDAO.resetPassword(emailId);
	}

}
