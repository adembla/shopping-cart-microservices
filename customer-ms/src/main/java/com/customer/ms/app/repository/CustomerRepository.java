package com.customer.ms.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.customer.ms.app.models.Customer;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
	   List<Customer> findByCustomerName(String customerName); 

}



