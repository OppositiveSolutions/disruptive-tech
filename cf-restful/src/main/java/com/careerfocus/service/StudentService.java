package com.careerfocus.service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.dao.CommonDAO;
import com.careerfocus.dao.MailDAO;
import com.careerfocus.dao.StudentDAO;
import com.careerfocus.dao.TestDAO;
import com.careerfocus.entity.Address;
import com.careerfocus.entity.Center;
import com.careerfocus.entity.Student;
import com.careerfocus.entity.User;
import com.careerfocus.entity.UserPhone;
import com.careerfocus.entity.UserProfilePic;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.model.response.StudentVO;
import com.careerfocus.repository.StudentRepository;
import com.careerfocus.repository.UserProfilePicRepository;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.DateUtils;
import com.careerfocus.util.StudentUtils;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StudentService {

	// private static final Logger log = Logger.getLogger(StudentService.class);

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StudentDAO studentDAO;

	@Autowired
	TestDAO testDAO;

	@Autowired
	MailDAO mailDAO;

	@Autowired
	CommonDAO commonDAO;

	@Autowired
	UserProfilePicRepository uppRepository;

	@Transactional
	public Response addStudent(AddStudentVO studentVO, MultipartFile image) throws IOException {
		List<Error> errors = StudentUtils.validate(studentVO);
		if (userRepository.findByUsername(studentVO.getEmailId()) != null)
			errors.add(new Error(ErrorCodes.EMAIL_EXISTS, ErrorCodes.EMAIL_EXISTS_MSG));

		if (errors != null && !errors.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED)
					.error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
		}

		User user = StudentUtils.createUserEntity(studentVO);
		System.out.println(studentVO);
		System.out.println(studentVO.getFirstName());
		System.out.println(user);
		System.out.println(user.getFirstName());
		user = userRepository.save(user);

		Student student = new Student(user.getUserId(), studentVO.getQualification(), 1, studentVO.getCenterId() + "",
				"paid", new Date(), new Center(studentVO.getCenterId()), studentVO.getType());
		studentRepository.save(student);
		if (image != null && image.getBytes() != null) {
			UserProfilePic pic = new UserProfilePic(user.getUserId(), image.getBytes());
			uppRepository.save(pic);
		}
		if (studentVO.getType() != 2)// 2 - online reg
			testDAO.createTestDefaultForANewUser(QuestionPaperService.DEFAUL_QP_BUNDLE, user.getUserId());
		try {
			mailDAO.welcomeMailUser(studentVO.getEmailId(), commonDAO.getPasswordForAUser(user.getUserId()),
					"Welcome to Career Focus. We enhance your confidence.");
		} catch (MalformedURLException | EmailException e) {
			e.printStackTrace();
		}
		studentVO.setUserId(user.getUserId());

		return Response.ok(studentVO).build();
	}

	public Response editStudent(AddStudentVO studentVO, int userId) {

		User user = userRepository.findOne(userId);
		Student student = studentRepository.findOne(studentVO.getUserId());
		if (user == null || student == null) {
			throw new RuntimeException();
		}

		// user.setUsername(studentVO.getEmailId());
		user.setFirstName(studentVO.getFirstName());
		user.setLastName(studentVO.getLastName());
		user.setGender(studentVO.getGender());
		user.setDob(DateUtils.convertMMDDYYYYToJavaDate(studentVO.getDob()));

		// studentDAO.deleteUserAddress(studentVO.getUserId());
		Address address = new Address(studentVO.getAddress(), studentVO.getLandMark(), studentVO.getCity(),
				studentVO.getState(), studentVO.getPinCode(), studentVO.getUserId());
		address.setUser(user);

		user.getAddress().clear();

		Set<Address> addressSet = new HashSet<>();
		addressSet.add(address);
		user.getAddress().addAll(addressSet);

		if (studentVO.getMobileNo() != null && !studentVO.getMobileNo().isEmpty()) {
			UserPhone phone = new UserPhone(studentVO.getMobileNo(), 1, true);
			phone.setUser(user);

			Set<UserPhone> phones = new HashSet<>();
			phones.add(phone);

			user.getUserPhones().clear();
			user.getUserPhones().addAll(phones);
		}
		user = userRepository.save(user);

		student.setQualification(studentVO.getQualification());
		// student.setCenter(new Center(studentVO.getCenterId()));
		// student.setCenterId(studentVO.getCenterId());
		studentRepository.save(student);

		studentVO.setUserId(user.getUserId());

		return Response.ok(studentVO).build();
	}

	public List<Map<String, Object>> getStudent(int pageSize, int pageNo) {
		// Pageable request = new PageRequest(pageNo - 1, pageSize);
		// return studentRepository.findAllStudents(request);
		return studentDAO.getStudents(pageSize, pageNo);
	}

	public Page<StudentVO> findStudentsByName(String key, int pageSize, int pageNo) {

		Pageable request = new PageRequest(pageNo - 1, pageSize);
		return studentRepository.searchStudentsByNameOrEmail("%" + key + "%", request);
	}

	@Transactional
	public void updateStudentExpiry(int userId, String expiryDate) {
		studentRepository.updateStudentExpiry(DateUtils.convertYYYYMMDDToJavaDate(expiryDate, "MM/dd/yyyy"), userId);
	}

	public byte[] getUserImage(int userId) {
		return uppRepository.findOne(userId).getPicture();
	}

	public boolean removeUserImage(int userId) {
		try {
			uppRepository.delete(userId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Response editUserImage(int userId, MultipartFile image) throws IOException {
		UserProfilePic pic = new UserProfilePic(userId, image.getBytes());
		return Response.ok(uppRepository.save(pic)).build();
	}

}
