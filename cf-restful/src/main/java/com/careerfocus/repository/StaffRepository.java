package com.careerfocus.repository;

import com.careerfocus.entity.Staff;
import com.careerfocus.model.response.StaffVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Modifying
    @Query("UPDATE Staff s SET s.joiningDate = ?1 WHERE s.userId = ?2")
    void updateStaffExpiry(Date date, int userId);

    @Query(value = "SELECT new com.careerfocus.model.response.StaffVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
            + " s.joiningDate, s.salary, u.username, p.phoneNo, s.status) FROM Staff s INNER JOIN s.user u "
            + "LEFT JOIN u.userPhones p WHERE p.isPrimary=1 ORDER BY u.firstName, u.lastName", nativeQuery = false)
    Page<StaffVO> findAllStaffs(Pageable page);

    @Query(value = "SELECT new com.careerfocus.model.response.StaffVO(u.userId, CONCAT(u.firstName, ' ', u.lastName), "
            + " s.joiningDate, s.salary, u.username, p.phoneNo, s.status) FROM Staff s INNER JOIN s.user u LEFT JOIN u.userPhones p "
            + "WHERE p.isPrimary=1 AND (LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(:key) OR LOWER(u.username) LIKE LOWER(:key)) "
            + "ORDER BY u.firstName, u.lastName", nativeQuery = false)
    Page<StaffVO> searchStaffsByNameOrEmail(@Param("key") String key, Pageable page);

}
