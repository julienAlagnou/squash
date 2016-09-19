package com.yungdu.us.squash.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yungdu.us.commons.lang.DBC;
import com.yungdu.us.squash.controllers.AbstractRestController;
import com.yungdu.us.squash.controllers.rest.resources.CategoryResource;
import com.yungdu.us.squash.controllers.rest.resources.CategoryResourceAssembler;
import com.yungdu.us.squash.data.Category;
import com.yungdu.us.squash.services.CategoryService;

@RestController
@RequestMapping(value = "/categories/{categoryId}")
public class CategoryController extends AbstractRestController
{

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public CategoryResource getCategory(@PathVariable final String categoryId)
	{
		DBC.checkNotBlank(categoryId, "categoryId is mandatory to create a category");

		Category category = categoryService.findCategoryById(categoryId).get();
		
		return resourceAssembler.toResource(category);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable final String categoryId)
	{
		DBC.checkNotBlank(categoryId, "categoryId is mandatory to delete a category");

		categoryService.deleteCategory(categoryId);
	}

}
