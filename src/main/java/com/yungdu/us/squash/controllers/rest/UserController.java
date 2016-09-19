package com.yungdu.us.squash.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.yungdu.us.squash.controllers.AbstractRestController;
import com.yungdu.us.squash.controllers.rest.resources.UserResource;
import com.yungdu.us.squash.controllers.rest.resources.UserResourceAssembler;
import com.yungdu.us.squash.data.User;
import com.yungdu.us.squash.services.UserService;

@RestController
@RequestMapping(value = "/users/{userId}")
public class UserController extends AbstractRestController
{

	@Autowired
	private UserService userService;

	@Autowired
	private UserResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public UserResource getUser(@PathVariable final String userId)
	{
		Preconditions.checkNotNull(userId, "userId is mandatory to create a user");
		
		User user = userService.findUserByUserId(userId).get();
		
		return resourceAssembler.toResource(user);
	}

}
