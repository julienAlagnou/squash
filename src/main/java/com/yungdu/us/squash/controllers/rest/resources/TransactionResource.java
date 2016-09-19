package com.yungdu.us.squash.controllers.rest.resources;

import org.springframework.hateoas.Resource;

import com.yungdu.us.squash.data.Transaction;

public class TransactionResource extends Resource<Transaction>
{

	TransactionResource(final Transaction transaction)
	{
		super(transaction);
	}

}
