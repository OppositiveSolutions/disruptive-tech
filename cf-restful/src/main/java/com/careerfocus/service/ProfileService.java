package com.careerfocus.service;

import com.careerfocus.dao.ProfileDAO;
import com.careerfocus.model.request.AddStudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ProfileService {

    @Autowired
    ProfileDAO profileDAO;

	public String getDetailsForMyProfile(int userId) {
		// TODO Auto-generated method stub
		return profileDAO.getDetailsForMyProfile(userId);
	}

	public String editMyProfile(AddStudentVO student) {
		// TODO Auto-generated method stub
		return null;
	}

}
