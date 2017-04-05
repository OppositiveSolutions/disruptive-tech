package com.careerfocus.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.entity.Address;
import com.careerfocus.entity.Student;
import com.careerfocus.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentDAO;

	public Collection<Student> getAllStudents() {
//		return studentDAO.findAll();
		return null;
	}

	@Transactional(rollbackOn = Exception.class)
	public Student addNewStudent(Student student) throws Exception {
//		studentDAO.save(student);

		student = new Student();
		student.setName("Kona");
		student.setRank(26);
		
		Address address = new Address();
		address.setAddress("Konark");
//		address.setStudent(student);
		
		student.setAddress(address);		
		studentDAO.save(student);
		
		return student;
	}

	public Student getStudent(int id) {
//		return studentDAO.findOne(id);
		return null;
	}
}
