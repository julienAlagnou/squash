package com.yungdu.us.squash.controllers.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.yungdu.us.squash.controllers.rest.CategoryController;
import com.yungdu.us.squash.data.Category;

@Component
public class CategoryResourceAssembler implements ResourceAssembler<Category, CategoryResource>
{

	@Override
	public CategoryResource toResource(final Category category)
	{
		// create the resource
		final CategoryResource categoryResource = new CategoryResource(category);

		categoryResource.add(linkTo(
				methodOn(CategoryController.class).getCategory(category.getId()))
				.withSelfRel());

		return categoryResource;
	}

}
