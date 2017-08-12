package com.careerfocus.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.dao.BundleDAO;
import com.careerfocus.entity.Bundle;
import com.careerfocus.repository.BundleRepository;
import com.careerfocus.util.response.Response;

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

	public Response editBundle(Bundle bundle) {
		Bundle existingBundle = bundleRepository.findOne(bundle.getBundleId());
		if (existingBundle == null) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).message(ErrorCodes.INVALID_BUNDLE_MSG).build();
		}
		existingBundle.setIsAvailable(bundle.isIsAvailable());
		existingBundle.setBundleCategory(bundle.getBundleCategory());
		existingBundle.setBundleId(bundle.getBundleId());
		existingBundle.setDescription(bundle.getDescription());
		existingBundle.setDiscountPercent(bundle.getDiscountPercent());
		existingBundle.setImageUrl(bundle.getImageUrl());
		existingBundle.setMrp(bundle.getMrp());
		existingBundle.setName(bundle.getName());
		existingBundle.setSellingPrice(bundle.getSellingPrice());
		return Response.ok(bundleRepository.save(existingBundle)).build();
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

	public int removeQpFromBundle(Integer bundleId, Integer qpId) {
		return bundleDAO.removeQpFromBundle(bundleId, qpId);
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

	public List<Map<String,Object>> getPurchasedQPBundleList(Integer userId) {
		return bundleDAO.getPurchasedQPBundleList(userId);
	}

}
