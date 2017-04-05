package com.careerfocus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerfocus.entity.Student;

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
	
	
	default void getStudents() {
		
	}
	
}
