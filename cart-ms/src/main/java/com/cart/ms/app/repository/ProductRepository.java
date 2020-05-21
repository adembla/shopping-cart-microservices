package com.cart.ms.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.cart.ms.app.models.Product;

public interface ProductRepository  extends CrudRepository<Product, Long> {
	   
}



