/**
 * 
 */
package com.products.ms.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.products.ms.app.models.Category;

/**
 * @author hitjoshi
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
