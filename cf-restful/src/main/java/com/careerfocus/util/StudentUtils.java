package com.careerfocus.util;

import java.util.ArrayList;
import java.util.List;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;

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
		
		return errors;
	}

}
