package com.careerfocus.util;

import com.careerfocus.constants.Constants;
import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.Address;
import com.careerfocus.entity.User;
import com.careerfocus.entity.UserPhone;
import com.careerfocus.model.request.AddStaffVO;
import com.careerfocus.util.response.Error;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaffUtils {

    public static List<Error> validate(AddStaffVO staff) {

        List<Error> errors = new ArrayList<Error>();
        if (!ValidationUtils.validateFirstName(staff.getFirstName())) {
            errors.add(new Error(ErrorCodes.INVALID_FIRSTNAME, ErrorCodes.INVALID_FIRSTNAME_MSG));
        }

        if (!ValidationUtils.validateLastName(staff.getLastName())) {
            errors.add(new Error(ErrorCodes.INVALID_LASTNAME, ErrorCodes.INVALID_LASTNAME_MSG));
        }

        if (!ValidationUtils.validateGender(staff.getGender())) {
            errors.add(new Error(ErrorCodes.INVALID_GENDER, ErrorCodes.INVALID_GENDER_MSG));
        }

        if (!ValidationUtils.isValidEmailAddress(staff.getEmailId())) {
            errors.add(new Error(ErrorCodes.INVALID_EMAIL_ID, ErrorCodes.INVALID_EMAIL_ID_MSG));
        }

        if (staff.getCenterId() == Constants.FALSE) {
            errors.add(new Error(ErrorCodes.INVALID_CENTER, ErrorCodes.INVALID_CENTER_MSG));
        }

        return errors;
    }

    public static User createUserEntity(AddStaffVO staffVO) {
        User user = new User(staffVO.getEmailId(), PasswordGenerator.generateSixDigitPassword(),
                Constants.ROLE_STAFF, staffVO.getFirstName(), staffVO.getLastName(), staffVO.getGender(),
				DateUtils.convertMMDDYYYYToJavaDate(staffVO.getDob()), 1, new Date());

        Address address = new Address(staffVO.getAddress(), staffVO.getLandMark(), staffVO.getCity(),
                staffVO.getState(), staffVO.getPinCode(), user.getUserId());
        address.setUser(user);
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address);
        user.setAddress(addressSet);

        if (staffVO.getMobileNo() != null && !staffVO.getMobileNo().isEmpty()) {
            UserPhone phone = new UserPhone(staffVO.getMobileNo(), 1, true);
            phone.setUser(user);
            Set<UserPhone> phones = new HashSet<>();
            phones.add(phone);
            user.setUserPhones(phones);
        }
        return user;
    }

}
