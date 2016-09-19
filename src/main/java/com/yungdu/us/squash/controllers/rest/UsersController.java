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
import com.yungdu.us.squash.controllers.rest.resources.UserResource;
import com.yungdu.us.squash.controllers.rest.resources.UserResourceAssembler;
import com.yungdu.us.squash.data.User;
import com.yungdu.us.squash.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UsersController extends AbstractRestController
{

	@Autowired
	private UserService userService;

	@Autowired
	private UserResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserResource createUser(@RequestBody final User user)
	{
		Preconditions.checkNotNull(user);

		// save user to DB
		final User newUser = userService.createUser(user);

		return resourceAssembler.toResource(newUser);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PagedResources<UserResource> findUsers(@PageableDefault(size = 5, page = 0) final Pageable page, final PagedResourcesAssembler<User> assembler)
	{
		final Page<User> usersPage = userService.findAllUsers(page);

		final Link selfLink = linkTo(methodOn(UsersController.class).findUsers(page, null)).withSelfRel();

		return assembler.toResource(usersPage, resourceAssembler, selfLink);
	}
}
