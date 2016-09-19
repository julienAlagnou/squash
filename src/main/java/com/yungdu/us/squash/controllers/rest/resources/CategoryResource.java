package com.yungdu.us.squash.controllers.rest.resources;


import org.springframework.hateoas.Resource;

import com.yungdu.us.squash.data.Category;

public class CategoryResource extends Resource<Category>
{

	CategoryResource(final Category category)
	{
		super(category);
	}

}
