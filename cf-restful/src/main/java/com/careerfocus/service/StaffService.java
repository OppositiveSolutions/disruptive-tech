package com.careerfocus.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.careerfocus.constants.Constants;
import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.Address;
import com.careerfocus.entity.Center;
import com.careerfocus.entity.Staff;
import com.careerfocus.entity.Student;
import com.careerfocus.entity.User;
import com.careerfocus.entity.UserPhone;
import com.careerfocus.model.request.AddStaffVO;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.model.response.StaffVO;
import com.careerfocus.repository.StaffRepository;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.DateUtils;
import com.careerfocus.util.PasswordGenerator;
import com.careerfocus.util.StaffUtils;
import com.careerfocus.util.StudentUtils;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;

@Service
public class StaffService {
	
	@Autowired
    StaffRepository staffRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Response addStaff(AddStaffVO staffVO) {
        List<Error> errors = StaffUtils.validate(staffVO);
        if (userRepository.findByUsername(staffVO.getEmailId()) != null)
            errors.add(new Error(ErrorCodes.EMAIL_EXISTS, ErrorCodes.EMAIL_EXISTS_MSG));

        if (errors != null && !errors.isEmpty()) {
            return Response.status(ErrorCodes.VALIDATION_FAILED)
                    .error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
        }

        User user = StaffUtils.createUserEntity(staffVO);
        user = userRepository.save(user);

        Staff staff = new Staff(user.getUserId(), staffVO.getQualification(), 1, "101", "dummy value",
                new Date(), new Center(staffVO.getCenterId()));
        staffRepository.save(staff);

        staffVO.setUserId(user.getUserId());

        return Response.ok(staffVO).build();
    }
    
    public Response editStaff(AddStaffVO  staffVO) {

    	User user = userRepository.findOne(staffVO.getUserId());
    	Staff staff = staffRepository.findOne(staffVO.getUserId());
        if (user == null || staff == null) {
            throw new RuntimeException();
        }

        user.setUsername(staffVO.getEmailId());
        user.setFirstName(staffVO.getFirstName());
        user.setLastName(staffVO.getLastName());
        user.setGender(staffVO.getGender());
        user.setDob(DateUtils.convertMMDDYYYYToJavaDate(staffVO.getDob()));

        Address address = new Address(staffVO.getAddress(), staffVO.getLandMark(), staffVO.getCity(),
                staffVO.getState(), staffVO.getPinCode());
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
        user = userRepository.save(user);

        staff.setQualification(staffVO.getQualification());
        staff.setCenter(new Center(staffVO.getCenterId()));
        staffRepository.save(staff);

        staffVO.setUserId(user.getUserId());

        return Response.ok(staffVO).build();
    }

    public Page<StaffVO> getStaff(int pageSize, int pageNo) {
        Pageable request = new PageRequest(pageNo - 1, pageSize);
        return staffRepository.findAllStaffs(request);
    }

    public Page<StaffVO> findStaffsByName(String key, int pageSize, int pageNo) {

        Pageable request = new PageRequest(pageNo - 1, pageSize);
        return staffRepository.searchStaffsByNameOrEmail("%" + key + "%", request);
    }
    
    @Transactional
    public void updateStaffExpiry(int userId) {
        staffRepository.updateStaffExpiry(userId);
    }

}
