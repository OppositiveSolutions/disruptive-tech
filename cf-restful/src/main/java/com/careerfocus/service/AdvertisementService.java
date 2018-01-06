package com.careerfocus.service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.AnnouncementImage;
import com.careerfocus.entity.Announcements;
import com.careerfocus.entity.SliderImage;
import com.careerfocus.repository.AnnouncementImageRepository;
import com.careerfocus.repository.AnnouncementRepository;
import com.careerfocus.repository.SliderImageRepository;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementService {

	@Autowired
	SliderImageRepository slideRepository;

	@Transactional
	public Response saveSliderImage(MultipartFile image) throws IOException {
		if (image == null || image.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).build();
		}

		SliderImage sImage = new SliderImage(image.getBytes());
		slideRepository.save(sImage);

		return Response.ok().build();
	}

	public void editSliderImage(int sliderId, MultipartFile image) throws IOException {
		SliderImage sliderImage = new SliderImage(sliderId, image.getBytes());
		slideRepository.save(sliderImage);
	}

	public List<SliderImage> getAllSlides() {
		return slideRepository.findAll();
	}

}
