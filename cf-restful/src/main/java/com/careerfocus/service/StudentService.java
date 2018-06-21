package com.careerfocus.service;

import com.careerfocus.constants.Constants;
//import com.careerfocus.constants.ErrorCodes;
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
//import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;

import org.apache.commons.mail.EmailException;
//import org.apache.log4j.Logger;
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
import java.util.List;
import java.util.Map;

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
		studentVO.setCenterId(Constants.DEFAULT_CENTER_ID);
		// List<Error> errors = StudentUtils.validate(studentVO);
		if (userRepository.findByUsername(studentVO.getEmailId()) != null)
			return null;
		// errors.add(new Error(ErrorCodes.EMAIL_EXISTS,
		// ErrorCodes.EMAIL_EXISTS_MSG));
		// if (errors != null && !errors.isEmpty()) {
		// return Response.status(ErrorCodes.VALIDATION_FAILED)
		// .error(new Error(ErrorCodes.VALIDATION_FAILED,
		// ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
		// }

		User user = StudentUtils.createUserEntity(studentVO);
		System.out.println(user.getFirstName());
		// user = userRepository.save(user);
		user = studentDAO.saveUser(user);

		Address address = new Address(studentVO.getAddress(), studentVO.getPlace(), studentVO.getCity(),
				studentVO.getState(), studentVO.getPinCode(), studentVO.getUserId());
		address.setUser(user);
		studentDAO.saveAddress(address);

		if (studentVO.getMobileNo() != null && !studentVO.getMobileNo().isEmpty()) {
			UserPhone phone = new UserPhone(studentVO.getMobileNo(), 1, true);
			phone.setUser(user);
			studentDAO.savePhone(phone);
		}

		Student student = new Student(user.getUserId(), studentVO.getQualification(), 1, studentVO.getCenterId() + "",
				"paid", new Date(), new Center(studentVO.getCenterId()), studentVO.getType() != 2 ? 1 : 2);
		studentRepository.save(student);
		if (image != null && image.getBytes() != null) {
			UserProfilePic pic = new UserProfilePic(user.getUserId(), commonDAO.resizeImage(image, true));
			uppRepository.save(pic);
		}
		if (studentVO.getType() != Constants.STUDENT_REGISTERED)// 2 - online
																// reg
			testDAO.createTestDefaultForANewUser(QuestionPaperService.DEFAUL_QP_BUNDLE, user.getUserId(), 0);
		// else
		// testDAO.createTestDefaultForANewUser(QuestionPaperService.DEFAUL_QP_BUNDLE,
		// user.getUserId(), 1);
		try {
			System.out.println("Email = " + studentVO.getEmailId());
			mailDAO.welcomeMailUser(studentVO.getEmailId(), commonDAO.getPasswordForAUser(user.getUserId()),
					"Welcome to Career Focus. We enhance your confidence.", student.getType() == 2 ? 0 : 1);
		} catch (MalformedURLException | EmailException e) {
			e.printStackTrace();
		}
		studentVO.setUserId(user.getUserId());
		return Response.ok(studentVO).build();
	}

	public Response editStudent(AddStudentVO studentVO, MultipartFile image) {

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

		try {
			if (image != null && image.getBytes() != null) {
				UserProfilePic pic = new UserProfilePic(user.getUserId(), commonDAO.resizeImage(image, true));
				uppRepository.save(pic);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (studentVO.getMobileNo() != null && !studentVO.getMobileNo().isEmpty()) {
			UserPhone phone = new UserPhone(studentVO.getMobileNo(), 1, true);
			phone.setUser(user);
			studentDAO.deleteUserPhone(studentVO.getUserId());
			studentDAO.savePhone(phone);
		}
		studentDAO.editUser(user);
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
		return studentDAO.getStudents(pageSize, pageNo, Constants.STUDENT_ACTIVE);
	}

	public List<Map<String, Object>> getStudentInactive(int pageSize, int pageNo) {
		return studentDAO.getStudents(pageSize, pageNo, Constants.STUDENT_REG_AND_ONCE_ACTIVE);
	}
	
	public List<Map<String, Object>> getStudentRegistered(int pageSize, int pageNo) {
		return studentDAO.getStudents(pageSize, pageNo, Constants.STUDENT_INACTIVE);
	}

	public boolean removeStudent(int userId) {
		try {
			return studentDAO.deleteStudent(userId);
		} catch (Exception e) {
			return false;
		}
	}

	public Page<StudentVO> findStudentsByName(String key, int pageSize, int pageNo) {

		Pageable request = new PageRequest(pageNo - 1, pageSize);
		return studentRepository.searchStudentsByNameOrEmail("%" + key + "%", request);
	}

	public Page<StudentVO> findStudentsByNameInactive(String key, int pageSize, int pageNo) {

		Pageable request = new PageRequest(pageNo - 1, pageSize);
		return studentRepository.searchStudentsByNameOrEmailInactive("%" + key + "%", request);
	}
	
	public Page<StudentVO> findStudentsByNameRegistered(String key, int pageSize, int pageNo) {

		Pageable request = new PageRequest(pageNo - 1, pageSize);
		return studentRepository.searchStudentsByNameOrEmailRegistered("%" + key + "%", request);
	}

	@Transactional
	public void updateStudentExpiry(int userId, String expiryDate) {
		studentRepository.updateStudentExpiry(DateUtils.convertYYYYMMDDToJavaDate(expiryDate, "MM/dd/yyyy"), userId);
	}

	public byte[] getUserImage(int userId) {
		try {
			return uppRepository.findOne(userId).getPicture();
		} catch (Exception e) {
			return uppRepository.findOne(1).getPicture();
		}
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
		UserProfilePic pic = new UserProfilePic(userId, commonDAO.resizeImage(image, true));
		return Response.ok(uppRepository.save(pic)).build();
	}

	public boolean activateStudent(int userId) {
		if (studentDAO.activateStudent(userId) == 1) {
			testDAO.createTestDefaultForANewUser(QuestionPaperService.DEFAUL_QP_BUNDLE, userId, 0);
			try {
				System.out.println("Email = " + commonDAO.getEmailIdFromUserId(userId));
				mailDAO.welcomeMailUser(commonDAO.getEmailIdFromUserId(userId), commonDAO.getPasswordForAUser(userId),
						"Your Career Focus Account has been Activated.", 1);
			} catch (MalformedURLException | EmailException e) {
				e.printStackTrace();
			}
			return testDAO.updateTestIsEnabledForAUser(userId, 1);
		} else
			return testDAO.updateTestIsEnabledForAUser(userId, 0);
	}

}
