package com.yungdu.us.squash.controllers.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.yungdu.us.squash.controllers.rest.AccountController;
import com.yungdu.us.squash.data.Account;

@Component
public class AccountResourceAssembler implements ResourceAssembler<Account, AccountResource>
{

	@Override
	public AccountResource toResource(final Account account)
	{
		// create the resource
		final AccountResource accountResource = new AccountResource(account);

		accountResource.add(linkTo(
				methodOn(AccountController.class).getAccount("userIdSample", account.getId()))
				.withSelfRel());

		return accountResource;
	}

}
