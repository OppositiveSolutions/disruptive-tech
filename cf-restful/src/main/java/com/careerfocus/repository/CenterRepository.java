package com.careerfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.Center;

public interface CenterRepository extends JpaRepository<Center, Integer> {
	
	public Center findByCenterCode(String centerCode);
	
}
