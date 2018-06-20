package com.careerfocus.service;

import com.careerfocus.dao.ProfileDAO;
import com.careerfocus.dao.StudentDAO;
import com.careerfocus.entity.Address;
import com.careerfocus.entity.Student;
import com.careerfocus.entity.User;
import com.careerfocus.entity.UserPhone;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.repository.StudentRepository;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.DateUtils;
import com.careerfocus.util.response.Response;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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

	@Autowired
	StudentDAO studentDAO;

	public Student getStudentDetails(int userId) {
		Date date = null;
		Student student = studentRepository.findOne(userId);
		if (student.getUser().getDob() != null) {
			System.out.println(student.getUser().getDob());
			try {
				// date = sm.parse(strDate);
				date = DateUtils.convertMYSQLDateTimeToMMddyyyy(student.getUser().getDob());
				System.out.println("Date = " + date);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (date != null)
				student.getUser().setDob(date);
		}
		return student;
	}

	public Map<String, Object> changePassword(int userId, String password) {
		return profileDAO.changePassword(userId, password);
	}

	public Map<String, Object> resetPassword(String emailId) {
		// String password = PasswordGenerator.generateSixDigitPassword();
		return profileDAO.resetPassword(emailId);
	}

	public Response editProfile(AddStudentVO studentVO) {

		User user = userRepository.findOne(studentVO.getUserId());
		Student student = studentRepository.findOne(studentVO.getUserId());
		if (user == null || student == null) {
			throw new RuntimeException();
		}

		user.setUsername(studentVO.getEmailId());
		user.setFirstName(studentVO.getFirstName());
		user.setLastName(studentVO.getLastName());
		user.setGender(studentVO.getGender());
		user.setDob(DateUtils.convertMMDDYYYYToJavaDate(studentVO.getDob()));

		Address address = new Address(studentVO.getAddress(), studentVO.getPlace(), studentVO.getCity(),
				studentVO.getState(), studentVO.getPinCode(), studentVO.getUserId());
		address.setUser(user);
		studentDAO.deleteUserAddress(studentVO.getUserId());
		studentDAO.saveAddress(address);

		if (studentVO.getMobileNo() != null && !studentVO.getMobileNo().isEmpty()) {
			UserPhone phone = new UserPhone(studentVO.getMobileNo(), 1, true);
			phone.setUser(user);
			studentDAO.deleteUserPhone(studentVO.getUserId());
			studentDAO.savePhone(phone);
		}
		studentDAO.editUser(user);
		student.setQualification(studentVO.getQualification());
		studentRepository.save(student);

		studentVO.setUserId(user.getUserId());

		return Response.ok(studentVO).build();
	}

}
