package com.yungdu.us.squash.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yungdu.us.squash.data.Category;

public interface CategoryService
{

	/**
	 * Create a category
	 *
	 * @param category the incoming category to create
	 * @return
	 */
	Category createCategory(Category category);

	/**
	 * Retrieves all stored categories.
	 *
	 * @param pageable pagination parameters
	 * @return
	 */
	Page<Category> findAllCategories(Pageable pageable);

	/**
	 * Retrieves the category with the given id.
	 *
	 * @param categoryId the id of the category to look for
	 * @return
	 */
	Optional<Category> findCategoryById(String categoryId);

	/**
	 * Delete the category with the given id.
	 * 
	 * @param categoryId the id of the category to delete
	 */
	void deleteCategory(String categoryId);


}
