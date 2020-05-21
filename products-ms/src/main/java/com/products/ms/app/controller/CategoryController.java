/**
 * 
 */
package com.products.ms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.ms.app.dto.PrimaryCategoryDTO;
import com.products.ms.app.models.Category;
import com.products.ms.app.service.CategoryService;

/**
 * @author hitjoshi
 *
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categroService; 
	
	@GetMapping(value="/", produces = "application/json")
	public List<PrimaryCategoryDTO> getPrimaryCategory(){        
		return categroService.fetchPrimaryCategories();
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	public Category getSecondaryCategories(@RequestParam("id") Long categoryId  ){        
		return categroService.fetchCategoryNameById(categoryId);
	}
	
	

}
