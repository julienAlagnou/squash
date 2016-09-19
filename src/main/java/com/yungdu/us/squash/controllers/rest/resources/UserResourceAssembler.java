package com.yungdu.us.squash.controllers.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.yungdu.us.squash.controllers.rest.UserController;
import com.yungdu.us.squash.data.User;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResource>
{

	@Override
	public UserResource toResource(final User user)
	{
		// create the resource
		final UserResource UserResource = new UserResource(user);

		UserResource.add(linkTo(
				methodOn(UserController.class).getUser(user.getUserId()))
				.withSelfRel());

		return UserResource;
	}

}
