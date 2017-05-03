package com.careerfocus.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.careerfocus.entity.Student;
import com.careerfocus.entity.User;
import com.careerfocus.model.request.AddStudentVO;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	
	@Query("SELECT u.firstName, u.lastName, s.qualification from Student s INNER JOIN s.user u")
	public List<AddStudentVO> getAllStudents();
}
