package com.careerfocus.repository;

import com.careerfocus.constants.Constants;
import com.careerfocus.entity.Student;
import com.careerfocus.model.response.StudentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Modifying
	@Query("UPDATE Student s SET s.expiryDate = ? WHERE s.userId = ?")
	void updateStudentExpiry(Date date, int userId);

	@Query(value = "SELECT new com.careerfocus.model.response.StudentVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
			+ "u.createdDate, s.expiryDate, u.username, p.phoneNo, s.status, s.centerId) FROM Student s INNER JOIN s.user u"
			+ " LEFT JOIN u.userPhones p WHERE (p.isPrimary=1 OR p.isPrimary IS NULL) AND u.role = "
			+ Constants.ROLE_STUDENT + " ORDER BY u.firstName, u.lastName", nativeQuery = false)
	Page<StudentVO> findAllStudents(Pageable page);

	@Query(value = "SELECT new com.careerfocus.model.response.StudentVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
			+ "u.createdDate, s.expiryDate, u.username, p.phoneNo, s.status, s.centerId) FROM Student s"
			+ " INNER JOIN s.user u LEFT JOIN u.userPhones p"
			+ " WHERE (p.isPrimary=1 OR p.isPrimary IS NULL) AND u.role = " + Constants.ROLE_STUDENT
			+ " AND (LOWER(CONCAT(u.firstName, ' ', u.lastName))"
			+ " LIKE LOWER(:key) OR LOWER(u.username) LIKE LOWER(:key))" + " ORDER BY u.firstName, u.lastName")
	Page<StudentVO> searchStudentsByNameOrEmail(@Param("key") String key, Pageable page);

}
