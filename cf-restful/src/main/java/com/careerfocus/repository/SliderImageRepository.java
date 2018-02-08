package com.careerfocus.repository;

import com.careerfocus.entity.SliderImage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SliderImageRepository extends JpaRepository<SliderImage, Integer> {

	@Query(value = "select si from SliderImage si where si.isCurrent = 1 ORDER BY si.sliderImageId DESC", nativeQuery = false)
	public List<SliderImage> findAllByIsCurrentOrderByIdAsc();
	
}
