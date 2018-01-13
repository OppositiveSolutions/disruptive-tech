package com.careerfocus.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.AchieverImage;
import com.careerfocus.entity.Achievers;
import com.careerfocus.repository.AchieversImageRepository;
import com.careerfocus.repository.AchieversRepository;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AchieversService {

	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	AchieversRepository achieverRepository;

	@Autowired
	AchieversImageRepository aiRepository;

	@Transactional
	public Response saveAchiever(String achieverJson, MultipartFile image) throws IOException {
		Achievers achiever = new ObjectMapper().readValue(achieverJson, Achievers.class);
		achiever.setImgFileName(image.getOriginalFilename());
		List<Error> errors = validateAchiever(achiever);
		if (errors != null && !errors.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED)
					.error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
		}

		achiever = achieverRepository.save(achiever);

		AchieverImage aImage = new AchieverImage(achiever.getAchieverId(), image.getBytes());
		aiRepository.save(aImage);

		if (achiever.isIsCurrent()) {
			updateCurrentAchieversOrder(achiever);
		}
		return Response.ok(achiever).build();
	}

	public Achievers editAchiever(Achievers achiever) {
		achiever = achieverRepository.save(achiever);
		if (achiever.isIsCurrent()) {
			updateCurrentAchieversOrder(achiever);
		}
		return achiever;
	}

	public List<Achievers> editAchievers(List<Achievers> achievers) {
		return achieverRepository.save(achievers);
	}

	public void editAchieverImage(int achieverId, MultipartFile image) throws IOException {
		AchieverImage aImage = new AchieverImage(achieverId, image.getBytes());
		aiRepository.save(aImage);
	}

	public List<Achievers> getAllAchievers() {
		return achieverRepository.findAllByOrderByIsCurrentDescOrderAsc();
	}

	public List<Achievers> getCurrentAchievers() {
		return achieverRepository.findByIsCurrentOrderByOrderAsc(true);
	}

	public List<Achievers> getOldAchievers() {
		return achieverRepository.findByIsCurrentOrderByOrderAsc(false);
	}

	public byte[] getAchieverImage(int achieverId) {
		return aiRepository.findOne(achieverId).getImage();
	}

	private List<Error> validateAchiever(Achievers achiever) {
		List<Error> subErrors = new ArrayList<Error>();
		if (achiever.getName() == null || achiever.getName().isEmpty()) {
			subErrors.add(new Error(ErrorCodes.ACHIEVER_NAME_EMPTY, ErrorCodes.ACHIEVER_NAME_EMPTY_MSG));
		}
		return subErrors;
	}

	private void updateCurrentAchieversOrder(Achievers achiever) {
		List<Achievers> aList = achieverRepository.findByIsCurrentAndOrderGreaterThan(true, achiever.getOrder() - 1);
		aList.forEach(ancment -> {
			log.debug("Achiever Name:" + achiever.getName());
			if (ancment.getAchieverId() != achiever.getAchieverId())
				ancment.setOrder(ancment.getOrder() + 1);
		});
		achieverRepository.save(aList);
	}

	public boolean removeAchievers(int achieverId) {
		try {
			achieverRepository.delete(achieverId);
			aiRepository.delete(achieverId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
