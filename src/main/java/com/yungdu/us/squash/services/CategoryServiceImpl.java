package com.yungdu.us.squash.services;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.yungdu.us.squash.dao.GenericDao;
import com.yungdu.us.squash.dao.repositories.CategoryRepository;
import com.yungdu.us.squash.data.Category;
import com.yungdu.us.squash.utils.exceptions.ElementAlreadyExistsException;

@Service
public class CategoryServiceImpl implements CategoryService
{
	private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Category createCategory(Category category)
	{
		Preconditions.checkNotNull(category);

		// check category doesn't exist already
		findCategoryById(category.getId()).ifPresent(p -> {
					throw new ElementAlreadyExistsException("Unable to create category. Category with ID (" + p.getId()
							+ ") already exists.");
		});

		// create the category
		final Category newCategory = new Category();
		newCategory.setId(category.getId());
		newCategory.setName(category.getName());
		newCategory.setDescription(category.getDescription());

		// save category
		repository.save(newCategory);

		LOG.info("Created category with ID: {}", newCategory.getId());

		return newCategory;
	}
	
	@Override
	public void deleteCategory(String categoryId)
	{
		Preconditions.checkArgument(StringUtils.isNotBlank(categoryId), "categoryId is blank");

		// check category doesn't exist already
		Category category = findCategoryById(categoryId).get();

		// delete category
		repository.delete(category);

		LOG.info("Deleted category with ID: {}", categoryId);
	}

	@Override
	public Page<Category> findAllCategories(final Pageable pageable)
	{
		Preconditions.checkNotNull(pageable);

		final Page<Category> category = repository.findAll(pageable);

		return category;
	}

	@Override
	public Optional<Category> findCategoryById(String categoryId)
	{
		Preconditions.checkNotNull(categoryId);

		return Optional.ofNullable(repository.findById(categoryId));
	}

}
