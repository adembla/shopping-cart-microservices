/**
 * 
 */
package com.cart.ms.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cart.ms.app.models.Cart;
import com.cart.ms.app.models.ProductInfo;

public interface CartRepository extends CrudRepository<Cart, Long> {
	
	
	@Query("SELECT new com.cart.ms.app.models.ProductInfo(p.id,p.productName,p.productDescription,p.unitPrice,c.quantity) from products p,cart c where p.id=c.id")
	List<ProductInfo> findProductDetails();

}
