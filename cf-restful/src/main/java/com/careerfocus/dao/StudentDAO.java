package com.careerfocus.dao;

import com.careerfocus.model.response.StudentVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDAO {

    @Autowired
    private JdbcTemplate template;

    public List<Map<String, Object>> getStudents(int pageSize, int pageNo) {

        String query = "SELECT u.user_id as userId, first_name as firstName, last_name as lastName,"
        		+ " created_date as createdDate, expiry_date as expiryDate, username, status, dob,"
        		+ " gender,street_address as streetAdress, land_mark as landMark, city,"
        		+ " state_id as stateId, pin_code as pinCode, phone_no as phoneNo FROM student s"
        		+ " INNER JOIN user u ON s.user_id = u.user_id"
        		+ " LEFT JOIN user_phone up on s.user_id = up.user_id and up.is_primary = 1"
        		+ " LEFT JOIN address a on a.user_id = s.user_id ORDER BY first_name, last_name ";

        if (pageSize > 0 && pageNo > 0) {
            query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        return template.queryForList(query);
    }
    
    public List<Map<String, Object>> getStaffs(int pageSize, int pageNo) {

        String query = "SELECT u.user_id as userId, first_name as firstName, last_name as lastName,"
        		+ " created_date as createdDate, username, status, dob, gender, street_address as streetAdress,"
        		+ " land_mark as landMark, city, state_id as stateId, pin_code as pinCode, phone_no as phoneNo"
        		+ " FROM staff s INNER JOIN user u ON s.user_id = u.user_id"
        		+ " LEFT JOIN user_phone up on s.user_id = up.user_id and up.is_primary = 1"
        		+ " LEFT JOIN address a on a.user_id = s.user_id ORDER BY first_name, last_name ";

        if (pageSize > 0 && pageNo > 0) {
            query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        return template.queryForList(query);
    }

    public Collection<StudentVO> searchStudentsByName(String name, int pageSize, int pageNo) {
        String query = "SELECT u.user_id, first_name, last_name, created_date, expiry_date, username, status FROM student s \n"
                + "INNER JOIN user u ON s.user_id = u.user_id WHERE role=1 AND CONCAT(first_name, ' ', last_name) LIKE ? "
                + "ORDER BY first_name, last_name";

        if (pageSize > 0 && pageNo > 0) {
            query += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }
        return StudentVO(query, "%" + name + "%");
    }

    private Collection<StudentVO> StudentVO(String query, Object... params) {
        return template.query(query, params, (result, arg1) -> {
//            String name = result.getString("first_name") + " " + result.getString("last_name");
            return null;
        });
    }
}
