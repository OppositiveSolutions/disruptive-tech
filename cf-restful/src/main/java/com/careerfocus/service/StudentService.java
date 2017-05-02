package com.careerfocus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.entity.Student;
import com.careerfocus.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public Student getStudent(int studentId) {
		
		return studentRepository.findOne(studentId);
	}

	
}
