package com.careerfocus.util;

import com.careerfocus.constants.Constants;
import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.Address;
import com.careerfocus.entity.User;
import com.careerfocus.entity.UserPhone;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.util.response.Error;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentUtils {

	public static List<Error> validate(AddStudentVO student) {

		List<Error> errors = new ArrayList<Error>();
		if (!ValidationUtils.validateFirstName(student.getFirstName())) {
			errors.add(new Error(ErrorCodes.INVALID_FIRSTNAME, ErrorCodes.INVALID_FIRSTNAME_MSG));
		}

		if (!ValidationUtils.validateLastName(student.getLastName())) {
			errors.add(new Error(ErrorCodes.INVALID_LASTNAME, ErrorCodes.INVALID_LASTNAME_MSG));
		}

		if (!ValidationUtils.validateGender(student.getGender())) {
			errors.add(new Error(ErrorCodes.INVALID_GENDER, ErrorCodes.INVALID_GENDER_MSG));
		}

		if (!ValidationUtils.isValidEmailAddress(student.getEmailId())) {
			errors.add(new Error(ErrorCodes.INVALID_EMAIL_ID, ErrorCodes.INVALID_EMAIL_ID_MSG));
		}

		if (student.getCenterId() == Constants.FALSE) {
			errors.add(new Error(ErrorCodes.INVALID_CENTER, ErrorCodes.INVALID_CENTER_MSG));
		}

		return errors;
	}

	public static User createUserEntity(AddStudentVO studentVO) {
		String password = null;
		if (studentVO.getType() == 2) {
			if (studentVO.getPassword() != null)
				password = studentVO.getPassword();
			else
				password = PasswordGenerator.generateSixDigitPassword();
		} else {
			password = PasswordGenerator.generateSixDigitPassword();
		}
		User user = new User(studentVO.getEmailId(), password, Constants.ROLE_STUDENT, studentVO.getFirstName(),
				studentVO.getLastName(), studentVO.getGender(), DateUtils.convertMMDDYYYYToJavaDate(studentVO.getDob()),
				1, new Date());

		Address address = new Address(studentVO.getAddress(), studentVO.getPlace(), studentVO.getCity(),
				studentVO.getState(), studentVO.getPinCode(), user.getUserId());
		Set<Address> addressSet = new HashSet<>();
		addressSet.add(address);
		user.setAddress(addressSet);

		if (studentVO.getMobileNo() != null && !studentVO.getMobileNo().isEmpty()) {
			UserPhone phone = new UserPhone(studentVO.getMobileNo(), 1, true);
			phone.setUser(user);
			Set<UserPhone> phones = new HashSet<>();
			phones.add(phone);
			user.setUserPhones(phones);
		}
		return user;
	}

}
