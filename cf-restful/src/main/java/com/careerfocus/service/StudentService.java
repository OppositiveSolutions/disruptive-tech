package com.careerfocus.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.StudentDAO;
import com.careerfocus.entity.Student;

@Service
public class StudentService {

	@Autowired
	StudentDAO studentDAO;

	public Collection<Student> getAllStudents() {
		return studentDAO.findAll();
	}

	@Transactional(rollbackOn = Exception.class)
	public Student addNewStudent(Student student) throws Exception {
		studentDAO.save(student);

		student = new Student();
		student.setName("Sini");
		student.setRank(21);

		if (1 + 1 == 2)
			throw new Exception("FUCK YOU");

		return studentDAO.save(student);
	}

	public Student getStudent(int id) {
		return studentDAO.findOne(id);
	}
}
