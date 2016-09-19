package com.yungdu.us.squash.controllers.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.yungdu.us.squash.controllers.AbstractRestController;
import com.yungdu.us.squash.controllers.rest.resources.AccountResource;
import com.yungdu.us.squash.controllers.rest.resources.AccountResourceAssembler;
import com.yungdu.us.squash.data.Account;
import com.yungdu.us.squash.services.AccountService;

@RestController
@RequestMapping(value="/users/{userId}/accounts")
public class AccountsController extends AbstractRestController
{

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public AccountResource createAccount(@PathVariable final String userId, @RequestBody final Account account)
	{
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId is mandatory to create a Account");
		Preconditions.checkNotNull(account);

		// save order to DB
		final Account newAccount = accountService.createAccount(userId, account);

		return resourceAssembler.toResource(newAccount);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PagedResources<AccountResource> findAccounts(@PathVariable final String userId, @PageableDefault(size = 5, page = 0) final Pageable page, final PagedResourcesAssembler<Account> assembler)
	{
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId is mandatory to create a Account");
		
		final Page<Account> accountsPage = accountService.findAllAccounts(userId, page);

		final Link selfLink = linkTo(methodOn(AccountsController.class).findAccounts(userId, page, null)).withSelfRel();

		return assembler.toResource(accountsPage, resourceAssembler, selfLink);
	}
}
