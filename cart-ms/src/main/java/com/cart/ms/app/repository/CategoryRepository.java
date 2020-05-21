/**
 * 
 */
package com.cart.ms.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.cart.ms.app.models.Category;

/**
 * @author hitjoshi
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
