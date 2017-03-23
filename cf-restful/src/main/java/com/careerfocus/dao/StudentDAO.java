package com.careerfocus.dao;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.careerfocus.model.Student;

//
@Repository
public class StudentDAO {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public Collection<Student> getAllStudents() {
		
		return jdbcTemplate.query(
                "SELECT id, name, rank FROM student",
                (rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("name"), rs.getInt("rank"))
        );
	}
	
}
