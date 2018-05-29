package com.careerfocus.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.dao.BundleDAO;
import com.careerfocus.entity.Bundle;
import com.careerfocus.entity.BundleImage;
import com.careerfocus.repository.BundleImageRepository;
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

	@Autowired
	BundleImageRepository biRepository;

	public Bundle saveBundle(Bundle bundle, MultipartFile image) throws IOException {
		bundle.setImgFileName(image.getOriginalFilename());
		bundle = bundleRepository.save(bundle);
		BundleImage bImage = new BundleImage(bundle.getBundleId(), image.getBytes());
		biRepository.save(bImage);
		return bundle;
	}

	public List<Map<String, Object>> getQPBundleList(Integer coachingType) {
		return bundleDAO.getQPBundleList(coachingType);
	}

	public List<Map<String, Object>> getBundles() {
		return bundleDAO.getBundles();
	}

	public List<Map<String, Object>> getCoachingTypeList() {
		return bundleDAO.getCoachingTypeList();
	}

	public static int NOT_AVAILABLE = 0;//disabled
	public static int AVAILABLE = 1;//enabled
	public static int DELETED = 2;

	public Response editBundle(Bundle bundle, MultipartFile image) throws IOException {
		Bundle existingBundle = bundleRepository.findOne(bundle.getBundleId());
		if (existingBundle == null) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).message(ErrorCodes.INVALID_BUNDLE_MSG).build();
		}
		if (image != null) {
			BundleImage bImage = new BundleImage(bundle.getBundleId(), image.getBytes());
			biRepository.save(bImage);
		}
		existingBundle.setIsAvailable(bundle.getIsAvailable());
		existingBundle.setBundleId(bundle.getBundleId());
		existingBundle.setDescription(bundle.getDescription());
		existingBundle.setDiscountPercent(bundle.getDiscountPercent());
		existingBundle.setMrp(bundle.getMrp());
		existingBundle.setName(bundle.getName());
		existingBundle.setSellingPrice(bundle.getSellingPrice());
		existingBundle.setImgFileName(image.getOriginalFilename());
		existingBundle.setCoachingType(bundle.getCoachingType());
		return Response.ok(bundleRepository.save(existingBundle)).build();
	}

	public int purchaseBundle(int userId, int bundleId) {
		return bundleDAO.purchaseBundle(userId, bundleId);
	}

	public Bundle getQPBundle(Integer bundleId) {
		return bundleRepository.findOne(bundleId);
	}

	public List<Map<String, Object>> getQPBundleQPs(Integer bundleId) {
		return bundleDAO.getBundleQPs(bundleId);
	}

	public int addQptoBundle(Integer bundleId, Integer qpId) {
		return bundleDAO.addQptoBundle(bundleId, qpId);
	}

	public int removeQpFromBundle(Integer bundleId, Integer qpId) {
		return bundleDAO.removeQpFromBundle(bundleId, qpId);
	}

	public Response deleteBundle(Integer bundleId) {
		// boolean status = true;
		// try {
		// bundleRepository.delete(bundleId);
		// } catch (Exception e) {
		// e.printStackTrace();
		// status = false;
		// }
		Bundle existingBundle = bundleRepository.findOne(bundleId);
		if (existingBundle == null) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).message(ErrorCodes.INVALID_BUNDLE_MSG).build();
		}
		existingBundle.setIsAvailable(DELETED);
		return Response.ok(bundleRepository.save(existingBundle)).build();
	}

	public Response changeBundleStatus(Integer bundleId, int status) {
		Bundle existingBundle = bundleRepository.findOne(bundleId);
		if (existingBundle == null) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).message(ErrorCodes.INVALID_BUNDLE_MSG).build();
		}
		existingBundle.setIsAvailable(status);
		return Response.ok(bundleRepository.save(existingBundle)).build();
	}

	public List<Map<String, Object>> getPurchasedQPBundleList(Integer userId) {
		return bundleDAO.getPurchasedQPBundleList(userId);
	}

	public byte[] getUserImage(int bundleId) {
		return biRepository.findOne(bundleId).getImage();
	}
}
