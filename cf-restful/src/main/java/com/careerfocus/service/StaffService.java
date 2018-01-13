package com.careerfocus.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.Center;
import com.careerfocus.entity.Staff;
import com.careerfocus.entity.User;
import com.careerfocus.model.request.AddStaffVO;
import com.careerfocus.model.response.StaffVO;
import com.careerfocus.repository.StaffRepository;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.DateUtils;
import com.careerfocus.util.StaffUtils;
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

    public Page<StaffVO> getStaff(int pageSize, int pageNo) {
        Pageable request = new PageRequest(pageNo - 1, pageSize);
        return staffRepository.findAllStaffs(request);
    }

    public Page<StaffVO> findStaffsByName(String key, int pageSize, int pageNo) {

        Pageable request = new PageRequest(pageNo - 1, pageSize);
        return staffRepository.searchStaffsByNameOrEmail("%" + key + "%", request);
    }

}
