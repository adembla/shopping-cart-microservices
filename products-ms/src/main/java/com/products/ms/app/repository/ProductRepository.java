package com.products.ms.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.products.ms.app.models.Product;

public interface ProductRepository  extends CrudRepository<Product, Long> {
	   
}



