package com.careerfocus.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.careerfocus.entity.Student;
import com.careerfocus.model.response.StudentVO;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Modifying
	@Query("UPDATE Student s SET s.expiryDate = ?1 WHERE s.userId = ?2")
	public void updateStudentExpiry(Date date, int userId);

	@Query(value = "SELECT new com.careerfocus.model.response.StudentVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
			+ "u.createdDate, s.expiryDate, u.username, p.phoneNo, s.status) FROM Student s INNER JOIN s.user u "
			+ "LEFT JOIN u.userPhones p WHERE p.isPrimary=1 ORDER BY u.firstName, u.lastName", nativeQuery = false)
	public Page<StudentVO> findAllStudents(Pageable page);

	@Query(value = "SELECT new com.careerfocus.model.response.StudentVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
			+ "u.createdDate, s.expiryDate, u.username, p.phoneNo, s.status) FROM Student s INNER JOIN s.user u LEFT JOIN u.userPhones p "
			+ "WHERE p.isPrimary=1 AND CONCAT(u.firstName, ' ', u.lastName) LIKE :name ORDER BY u.firstName, u.lastName", nativeQuery = false)
	public Page<StudentVO> searchStudentsByName(@Param("name") String name, Pageable page);

}
