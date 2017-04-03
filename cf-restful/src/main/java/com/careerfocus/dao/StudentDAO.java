package com.careerfocus.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.careerfocus.entity.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {

//	@Autowired
//    private JdbcTemplate jdbcTemplate;
	
//	public Collection<Student> getAllStudents() {
//		
//		return jdbcTemplate.query(
//                "SELECT id, name, rank FROM student",
//                (rs, rowNum) -> new Student(rs.getInt("id"),
//                        rs.getString("name"), rs.getInt("rank"))
//        );
//	}
	
}
