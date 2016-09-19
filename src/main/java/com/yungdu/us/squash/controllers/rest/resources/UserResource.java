package com.yungdu.us.squash.controllers.rest.resources;


import org.springframework.hateoas.Resource;

import com.yungdu.us.squash.data.User;

public class UserResource extends Resource<User>
{

	UserResource(final User user)
	{
		super(user);
	}

}
