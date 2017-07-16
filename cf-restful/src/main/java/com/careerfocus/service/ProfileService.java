package com.careerfocus.service;

import com.careerfocus.dao.ProfileDAO;
import com.careerfocus.entity.Bundle;
import com.careerfocus.repository.BundleRepository;
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

}
