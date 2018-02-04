package com.careerfocus.service;

import com.careerfocus.constants.Constants;
import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.Address;
import com.careerfocus.entity.Center;
import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.Student;
import com.careerfocus.entity.User;
import com.careerfocus.entity.UserPhone;
import com.careerfocus.model.request.AddStudentVO;
import com.careerfocus.model.response.StudentVO;
import com.careerfocus.repository.StudentRepository;
import com.careerfocus.repository.UserRepository;
import com.careerfocus.util.DateUtils;
import com.careerfocus.util.PasswordGenerator;
import com.careerfocus.util.StudentUtils;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Response addStudent(AddStudentVO studentVO) {
        List<Error> errors = StudentUtils.validate(studentVO);
        if (userRepository.findByUsername(studentVO.getEmailId()) != null)
            errors.add(new Error(ErrorCodes.EMAIL_EXISTS, ErrorCodes.EMAIL_EXISTS_MSG));

        if (errors != null && !errors.isEmpty()) {
            return Response.status(ErrorCodes.VALIDATION_FAILED)
                    .error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
        }

        User user = StudentUtils.createUserEntity(studentVO);
        user = userRepository.save(user);

        Student student = new Student(user.getUserId(), studentVO.getQualification(), 1, studentVO.getCenterId()+"", "paid",
                new Date(), new Center(studentVO.getCenterId()));
        studentRepository.save(student);

        studentVO.setUserId(user.getUserId());

        return Response.ok(studentVO).build();
    }
    
    public Response editStudent(AddStudentVO studentVO) {

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

        Address address = new Address(studentVO.getAddress(), studentVO.getLandMark(), studentVO.getCity(),
                studentVO.getState(), studentVO.getPinCode());
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
        user = userRepository.save(user);

        student.setQualification(studentVO.getQualification());
        student.setCenter(new Center(studentVO.getCenterId()));
        student.setCenterId(studentVO.getCenterId());
        studentRepository.save(student);

        studentVO.setUserId(user.getUserId());

        return Response.ok(studentVO).build();
    }

    public Page<StudentVO> getStudent(int pageSize, int pageNo) {
        Pageable request = new PageRequest(pageNo - 1, pageSize);
        return studentRepository.findAllStudents(request);
    }

    public Page<StudentVO> findStudentsByName(String key, int pageSize, int pageNo) {

        Pageable request = new PageRequest(pageNo - 1, pageSize);
        return studentRepository.searchStudentsByNameOrEmail("%" + key + "%", request);
    }

    @Transactional
    public void updateStudentExpiry(int userId, String expiryDate) {
        studentRepository.updateStudentExpiry(DateUtils.convertYYYYMMDDToJavaDate(expiryDate), userId);
    }
    
}
