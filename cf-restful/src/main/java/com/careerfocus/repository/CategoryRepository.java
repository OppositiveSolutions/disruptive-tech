package com.careerfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByNameContainingIgnoreCase(String name);
}
