package com.careerfocus.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.BundleDAO;
import com.careerfocus.entity.Bundle;
import com.careerfocus.repository.BundleRepository;

/**
 * Created by sarath on 04/06/17.
 */
@Service
public class BundleService {

	@Autowired
	BundleRepository bundleRepository;

	@Autowired
	BundleDAO bundleDAO;

	public Bundle saveBundle(Bundle bundle) {
		return bundleRepository.save(bundle);
	}

	public List<Map<String, Object>> getQPBundleList(Integer coachingType) {
		return bundleDAO.getQPBundleList(coachingType);
	}

	public Bundle editBundle(Bundle bundle) {
		return bundleRepository.save(bundle);
	}
	
	public int purchaseBundle(int userId, int bundleId) {
		return bundleDAO.purchaseBundle(userId, bundleId);
	}

	public Bundle getQPBundle(Integer bundleId) {
		return bundleRepository.findOne(bundleId);
	}

	public List<Map<String,Object>> getQPBundleQPs(Integer bundleId) {
		return bundleDAO.getBundleQPs(bundleId);
	}

	public int addQptoBundle(Integer bundleId, Integer qpId) {
		return bundleDAO.addQptoBundle(bundleId, qpId);
	}

	public int removeQptoBundle(Integer bundleId, Integer qpId) {
		return bundleDAO.removeQpfromBundle(bundleId, qpId);
	}

	public boolean deleteBundle(Integer bundleId) {
		boolean status = true;
		try {
			bundleRepository.delete(bundleId);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

}
