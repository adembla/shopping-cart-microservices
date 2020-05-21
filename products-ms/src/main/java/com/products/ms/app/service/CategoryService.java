/**
 * 
 */
package com.products.ms.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.ms.app.dto.PrimaryCategoryDTO;
import com.products.ms.app.models.Category;
import com.products.ms.app.repository.CategoryRepository;

/**
 * @author hitjoshi
 *
 */
@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<PrimaryCategoryDTO> fetchPrimaryCategories(){
		Iterable<Category> primaryCategories = categoryRepository.findAll();
		List<PrimaryCategoryDTO> primaryCategoryDTOs = new ArrayList<>();
		for(Category pr: primaryCategories) {
			PrimaryCategoryDTO primaryCategoryDTO = new PrimaryCategoryDTO();
			primaryCategoryDTO.setCategoryId(pr.getId());
			primaryCategoryDTO.setCategoryName(pr.getCategoryName());
			primaryCategoryDTOs.add(primaryCategoryDTO);
		}
		return primaryCategoryDTOs;
	}
	
	
	public Category fetchCategoryNameById(Long categoryId){
		Category category = categoryRepository.findOne(categoryId);
		return category;
	}
}
