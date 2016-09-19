package com.yungdu.us.squash.controllers.rest;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/users/{userId}/accounts/{accountId}")
public class AccountController extends AbstractRestController
{

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public AccountResource getAccount(@PathVariable final String userId, @PathVariable final String accountId)
	{
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId is mandatory to create a Account");
		Preconditions.checkArgument(StringUtils.isNotBlank(accountId), "accountId is mandatory to create a Account");

		Account account = accountService.findAccountByAccountId(userId, accountId).get();
		
		return resourceAssembler.toResource(account);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable final String userId, @PathVariable final String accountId)
	{
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId is mandatory to create a Account");
		Preconditions.checkArgument(StringUtils.isNotBlank(accountId), "accountId is mandatory to delete a Account");

		accountService.deleteAccount(userId, accountId);
	}

}
