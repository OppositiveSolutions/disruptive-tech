package com.careerfocus.service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.Testimonial;
import com.careerfocus.entity.TestimonialImage;
import com.careerfocus.entity.User;
import com.careerfocus.model.response.TestimonialVO;
import com.careerfocus.repository.TestimonialImageRepository;
import com.careerfocus.repository.TestimonialRepository;
import com.careerfocus.util.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestimonialService {

	@Autowired
	TestimonialRepository testimonialRepo;

	@Autowired
	TestimonialImageRepository tiRepository;

	public Testimonial saveTestimonials(HttpServletRequest request, String testimonialJson, MultipartFile image)
			throws IOException {
		HttpSession session = request.getSession();
		Testimonial testimonial = new ObjectMapper().readValue(testimonialJson, Testimonial.class);
		testimonial.setUser(new User(Integer
				.valueOf(session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME).toString())));
		testimonialRepo.save(testimonial);
		TestimonialImage aImage = new TestimonialImage(testimonial.getTestimonialId(), image.getBytes());
		tiRepository.save(aImage);
		return testimonial;
	}

	public Response editTestimonials(String testimonialJson, MultipartFile image) throws IOException {
		Testimonial testimonial = new ObjectMapper().readValue(testimonialJson, Testimonial.class);
		Testimonial existingTestimonial = testimonialRepo.findOne(testimonial.getTestimonialId());
		if (existingTestimonial == null) {
			return Response.status(ErrorCodes.VALIDATION_FAILED).message(ErrorCodes.ACHIEVER_NAME_EMPTY_MSG).build();
		}
		if (image != null) {
			TestimonialImage tImage = new TestimonialImage(testimonial.getTestimonialId(), image.getBytes());
			tiRepository.save(tImage);
			existingTestimonial.setTestimonialImage(tImage);
			existingTestimonial.setImgFileName(image.getOriginalFilename());
		}
		existingTestimonial.setContact(testimonial.getContact());
		existingTestimonial.setContent(testimonial.getContent());
		existingTestimonial.setDescription(testimonial.getDescription());
		existingTestimonial.setName(testimonial.getName());
		return Response.ok(testimonialRepo.save(existingTestimonial)).build();
	}

	public List<TestimonialVO> getAllTestimonials() {
		List<TestimonialVO> testimonials = new ArrayList<TestimonialVO>();
		List<TestimonialVO> testimonialVOs = testimonialRepo.getAllTestimonials();
		for (TestimonialVO vo : testimonialVOs) {
			vo.setImage(null);
			testimonials.add(vo);
		}
		return testimonials;
	}

	public List<TestimonialVO> getTestimonials() {
		return testimonialRepo.getAllTestimonials();
	}

	public List<TestimonialVO> getAllTestimonialsOfUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = Integer
				.valueOf(session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME).toString());
		return testimonialRepo.getAllTestimonials(userId);
	}

	public boolean deleteTestimonial(Integer testimonialId) {
		try {
			testimonialRepo.delete(testimonialId);
			tiRepository.delete(testimonialId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public byte[] getTestimonialImage(int testimonialId) {
		return tiRepository.findOne(testimonialId).getImage();
	}

}
