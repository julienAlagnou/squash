package com.yungdu.us.squash.controllers.rest.resources;


import org.springframework.hateoas.Resource;

import com.yungdu.us.squash.data.Account;

public class AccountResource extends Resource<Account>
{

	AccountResource(final Account account)
	{
		super(account);
	}

}
