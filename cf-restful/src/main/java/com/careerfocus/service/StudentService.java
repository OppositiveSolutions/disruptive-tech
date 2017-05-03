package com.careerfocus.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.constants.Constants;
import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.dao.StudentDAO;
import com.careerfocus.entity.Student;
import com.careerfocus.entity.User;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.model.response.StudentVO;
import com.careerfocus.repository.StudentRepository;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.DateUtils;
import com.careerfocus.util.PasswordGenerator;
import com.careerfocus.util.StudentUtils;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;

@Service
public class StudentService {

	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StudentDAO studentDAO;

	public Response addStudent(AddStudentVO studentVO) {
		List<Error> errors = StudentUtils.validate(studentVO);
		if (errors != null && !errors.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED)
					.error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
		}

		User user = new User(studentVO.getEmailId(), PasswordGenerator.generateSixDigitPassword(),
				Constants.ROLE_STUDENT, studentVO.getFirstName(), studentVO.getLastName(), studentVO.getGender(),
				DateUtils.convertMMDDYYYYToJavaDate(studentVO.getDob()));
		user = userRepository.save(user);

		Student student = new Student(user.getUserId(), studentVO.getQualification(), 1, "101", "dummy value",
				new Date());
		studentRepository.save(student);

		studentVO.setUserId(user.getUserId());
		return Response.ok(studentVO).build();
	}

	public Collection<StudentVO> getStudent(int pageSize, int pageNo) {

		return studentDAO.getStudents(0, 0);
	}

}
