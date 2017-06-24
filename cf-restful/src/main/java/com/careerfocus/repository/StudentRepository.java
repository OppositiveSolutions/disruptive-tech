package com.careerfocus.repository;

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
    @Query("UPDATE Student s SET s.expiryDate = ?1 WHERE s.userId = ?2")
    void updateStudentExpiry(Date date, int userId);

    @Query(value = "SELECT new com.careerfocus.model.response.StudentVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
            + "u.createdDate, s.expiryDate, u.username, p.phoneNo, s.status) FROM Student s INNER JOIN s.user u "
            + "LEFT JOIN u.userPhones p WHERE p.isPrimary=1 ORDER BY u.firstName, u.lastName", nativeQuery = false)
    Page<StudentVO> findAllStudents(Pageable page);

    @Query(value = "SELECT new com.careerfocus.model.response.StudentVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
            + "u.createdDate, s.expiryDate, u.username, p.phoneNo, s.status) FROM Student s INNER JOIN s.user u LEFT JOIN u.userPhones p "
            + "WHERE p.isPrimary=1 AND (LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(:key) OR LOWER(u.username) LIKE LOWER(:key)) "
            + "ORDER BY u.firstName, u.lastName", nativeQuery = false)
    Page<StudentVO> searchStudentsByNameOrEmail(@Param("key") String key, Pageable page);

}
