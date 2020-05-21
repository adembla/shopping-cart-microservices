package com.order.ms.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.order.ms.app.models.Invoice;

public interface InvoiceRepository  extends CrudRepository<Invoice, Long> {

	 List<Invoice> findByCustomerId(Long customerId); 
	   
}



