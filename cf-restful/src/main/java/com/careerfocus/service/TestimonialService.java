package com.careerfocus.service;

import com.careerfocus.entity.Testimonial;
import com.careerfocus.entity.User;
import com.careerfocus.model.response.TestimonialVO;
import com.careerfocus.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TestimonialService {

    @Autowired
    TestimonialRepository testiclesRepo;

    public Testimonial saveTesticles(HttpServletRequest request, Testimonial testicle) {
        HttpSession session = request.getSession();
        testicle.setUser(new User(Integer
                .valueOf(session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME).toString())));
        return testiclesRepo.save(testicle);
    }

    public List<TestimonialVO> getAllTesticles() {
        return testiclesRepo.getAllTestimonials();
    }

    public List<TestimonialVO> getAllTestimonialsOfUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = Integer
                .valueOf(session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME).toString());
        return testiclesRepo.getAllTestimonials(userId);
    }

}
