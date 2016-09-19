package com.yungdu.us.squash.controllers.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.yungdu.us.squash.controllers.AbstractRestController;
import com.yungdu.us.squash.controllers.rest.resources.CategoryResource;
import com.yungdu.us.squash.controllers.rest.resources.CategoryResourceAssembler;
import com.yungdu.us.squash.data.Category;
import com.yungdu.us.squash.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesController extends AbstractRestController
{

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CategoryResource createCategory(@RequestBody final Category category)
	{
		Preconditions.checkNotNull(category);

		// save category to DB
		final Category newCategory = categoryService.createCategory(category);

		return resourceAssembler.toResource(newCategory);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PagedResources<CategoryResource> findCategories(@PageableDefault(size = 5, page = 0) final Pageable page, final PagedResourcesAssembler<Category> assembler)
	{
		final Page<Category> categoriesPage = categoryService.findAllCategories(page);

		final Link selfLink = linkTo(methodOn(CategoriesController.class).findCategories(page, null)).withSelfRel();

		return assembler.toResource(categoriesPage, resourceAssembler, selfLink);
	}
}
