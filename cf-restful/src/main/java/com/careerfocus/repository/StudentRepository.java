package com.careerfocus.repository;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.careerfocus.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Modifying
	@Query("UPDATE Student s SET s.expiryDate = ?1 WHERE s.userId = ?2")
	public void updateStudentExpiry(Date date, int userId);
	
	
	
}
