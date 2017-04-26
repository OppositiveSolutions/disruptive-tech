package com.careerfocus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.careerfocus.entity.Category;
import com.careerfocus.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repository;
	
	public Category addCategory(Category category) {
		return repository.save(category);
	}
	
	public void deleteCategory(int categoryId) {
		repository.delete(categoryId);
	}
	
	public List<Category> getAllCategories() {
		return repository.findAll();
	}
	
	public Page<Category> getCategories(int pageSize, int pageNumber) {
		Pageable page = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC, "categoryId");
		return repository.findAll(page);
	}
	
	public Category getCategory(int categoryId) {
		return repository.findOne(categoryId);
	}
}
