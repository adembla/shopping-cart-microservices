/**
 * 
 */
package com.order.ms.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.order.ms.app.models.Invoice;
import com.order.ms.app.models.Items;

public interface ItemsRepository extends CrudRepository<Items, Long> {

	 List<Items> findByInvoiceId(Invoice invoiceId); 
}
