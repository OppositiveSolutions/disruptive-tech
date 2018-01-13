package com.careerfocus.service;

import com.careerfocus.entity.Testimonial;
import com.careerfocus.entity.TestimonialImage;
import com.careerfocus.entity.User;
import com.careerfocus.model.response.TestimonialVO;
import com.careerfocus.repository.TestimonialImageRepository;
import com.careerfocus.repository.TestimonialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
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

	public List<TestimonialVO> getAllTestimonials() {
		return testimonialRepo.getAllTestimonials();
	}

	public List<TestimonialVO> getAllTestimonialsOfUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = Integer
				.valueOf(session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME).toString());
		return testimonialRepo.getAllTestimonials(userId);
	}

}
