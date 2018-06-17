package com.careerfocus.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

	public Response editAchiever(String achieverJson, MultipartFile image) throws IOException {
		Achievers achiever = new ObjectMapper().readValue(achieverJson, Achievers.class);
		Achievers existingAchiever = achieverRepository.findOne(achiever.getAchieverId());
		if (existingAchiever == null) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).message(ErrorCodes.ACHIEVER_NAME_EMPTY_MSG).build();
		}
		if (image != null) {
			AchieverImage aImage = new AchieverImage(achiever.getAchieverId(), image.getBytes());
			aiRepository.save(aImage);
			existingAchiever.setachieverImage(aImage);
			existingAchiever.setImgFileName(image.getOriginalFilename());
		}
		existingAchiever.setContact(achiever.getContact());
		existingAchiever.setDescription(achiever.getDescription());
		existingAchiever.setName(achiever.getName());
		existingAchiever.setYear(achiever.getYear());
		return Response.ok(achieverRepository.save(existingAchiever)).build();
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

	public List<Achievers> getAchieversForHomePage() {
		List<Achievers> lastFourAchievers = new ArrayList<Achievers>();
		List<Achievers> achievers = achieverRepository.findAllByOrderByYearDescNameAscOrderAsc();

		Random rn = new Random();
		List<Integer> randoms = new ArrayList<Integer>();
		int answer = 0;
		int size = 4;
		if (achievers.size() > 4)
			size = 4;
		else
			size = achievers.size();
		do {
			answer = rn.nextInt(achievers.size());
			if (randoms.size() == 0)
				randoms.add(answer);
			for (int r : randoms)
				if (r != answer) {
					randoms.add(answer);
					break;
				}
		} while (randoms.size() < size);
		for (int i : randoms) {
			Achievers achiever = achievers.get(i);
			if (achiever.getachieverImage() == null)
				achiever.setachieverImage(aiRepository.findOne(achiever.getAchieverId()));
			lastFourAchievers.add(achiever);
		}
		return lastFourAchievers;
	}

	public List<Map<String, Object>> getAllAchieversByYear() {
		boolean hasYear = true;
		Map<String, Object> yearMap = null;
		List<Achievers> achieversInAYear = null;
		List<Map<String, Object>> achieversYearWise = new ArrayList<Map<String, Object>>();
		List<Achievers> achievers = achieverRepository.findAllByOrderByYearDescNameAscOrderAsc();
		List<Integer> years = new ArrayList<Integer>();
		for (Achievers achiever : achievers) {
			hasYear = false;
			for (int year : years) {
				if (achiever.getYear() == year) {
					hasYear = true;
					break;
				}
			}
			if (!hasYear || years.size() == 0)
				years.add(achiever.getYear());
		}
		for (int year : years) {
			achieversInAYear = new ArrayList<Achievers>();
			yearMap = new HashMap<String, Object>();
			yearMap.put("Year", year);
			for (Achievers achiever : achievers) {
				if (achiever.getYear() == year) {
					achieversInAYear.add(achiever);
				}
				yearMap.put("achievers", achieversInAYear);
			}
			achieversYearWise.add(yearMap);
		}
		return achieversYearWise;
	}

	public List<Achievers> getAllAchieversList(int year) {
		List<Achievers> achieversInAYear = null;
		List<Achievers> achievers = achieverRepository.findAllByOrderByYearDescNameAscOrderAsc();
		if (year != 0) {
			achieversInAYear = new ArrayList<Achievers>();
			for (Achievers achiever : achievers) {
				if (achiever.getYear() == year) {
					achieversInAYear.add(achiever);
				}
			}
		} else
			return achievers;
		return achieversInAYear;
	}

	public List<Integer> getAllAchieversYears() {
		boolean hasYear = true;
		List<Achievers> achievers = achieverRepository.findAllByOrderByYearDescNameAscOrderAsc();
		List<Integer> years = new ArrayList<Integer>();
		for (Achievers achiever : achievers) {
			hasYear = false;
			for (int year : years) {
				if (achiever.getYear() == year) {
					hasYear = true;
					break;
				}
			}
			if (!hasYear || years.size() == 0)
				years.add(achiever.getYear());
		}
		return years;
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
