package com.careerfocus.service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.SliderAnnouncement;
import com.careerfocus.entity.SliderImage;
import com.careerfocus.repository.SliderAnnouncementRepository;
import com.careerfocus.repository.SliderImageRepository;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class AdvertisementService {

	@Autowired
	SliderImageRepository siRepository;

	@Autowired
	SliderAnnouncementRepository saRepository;

	@Transactional
	public Response saveSliderImage(MultipartFile image) throws IOException {
		if (image == null || image.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).build();
		}

		SliderImage sImage = new SliderImage(image.getBytes());
		sImage.setIsCurrent(1);
		siRepository.save(sImage);

		return Response.ok().build();
	}

	public void editSliderImage(int sliderId, MultipartFile image) throws IOException {
		SliderImage sliderImage = new SliderImage(sliderId, image.getBytes());
		siRepository.save(sliderImage);
	}

	public List<SliderImage> getAllSliderImages() {
		return siRepository.findAllByIsCurrentOrderByIdAsc();
	}

	public byte[] getSliderImage(int sliderImageId) {
		return siRepository.findOne(sliderImageId).getImage();
	}

	public void deleteSliderImage(Integer sliderImageId) {
		siRepository.delete(sliderImageId);

	}

	// public void updateSliderImageIsCurrent(int sliderImageId) throws
	// IOException {
	// siRepository.updateSliderImageIsCurrent(sliderImageId);
	// }

	@Transactional
	public Response saveSliderAnnouncement(String announcement) throws IOException {
		if (announcement == null || announcement.equalsIgnoreCase("")) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).build();
		}

		SliderAnnouncement sImage = new SliderAnnouncement(announcement);
		saRepository.save(sImage);

		return Response.ok().build();
	}

	public void editSliderAnnouncement(int sliderId, String announcement) throws IOException {
		SliderAnnouncement sliderImage = new SliderAnnouncement(sliderId, announcement);
		saRepository.save(sliderImage);
	}

	public List<SliderAnnouncement> getAllSliderAnnouncements() {
		return saRepository.findAll();
	}

	public String getSliderAnnouncement(int sliderAnnouncementId) {
		return saRepository.findOne(sliderAnnouncementId).getAnnouncement();
	}

	public void deleteSliderAnnouncement(Integer sliderAnnouncementId) {
		saRepository.delete(sliderAnnouncementId);

	}

	// public void updateSliderAnnouncementIsCurrent(int sliderAnnouncementId)
	// throws IOException {
	// saRepository.updateSliderAnnouncementIsCurrent(sliderAnnouncementId);
	// }

}
