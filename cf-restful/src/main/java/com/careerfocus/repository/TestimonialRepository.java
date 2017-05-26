package com.careerfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.careerfocus.entity.Testimonial;
import com.careerfocus.model.response.TestimonialVO;

public interface TestimonialRepository extends JpaRepository<Testimonial, Integer> {

	List<Testimonial> findAllByOrderByDateDesc();

	@Query("SELECT new com.careerfocus.model.response.TestimonialVO(t.testimonialId, t.content, t.date, CONCAT(u.firstName, ' ', u.lastName), u.userId) "
			+ "FROM Testimonial t INNER JOIN t.user u")
	List<TestimonialVO> getAllTestimonials();
	
	@Query("SELECT new com.careerfocus.model.response.TestimonialVO(t.testimonialId, t.content, t.date, CONCAT(u.firstName, ' ', u.lastName), u.userId) "
			+ "FROM Testimonial t INNER JOIN t.user u WHERE u.userId = :userId")
	List<TestimonialVO> getAllTestimonials(@Param("userId") int userId);

}
