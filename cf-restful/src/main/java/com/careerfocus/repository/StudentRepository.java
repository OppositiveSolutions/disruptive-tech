package com.careerfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
