package com.careerfocus.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.StudentDAO;
import com.careerfocus.model.Student;


@Service
public class StudentService {

	@Autowired
	StudentDAO studentDAO;
	
	public Collection<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}
}
