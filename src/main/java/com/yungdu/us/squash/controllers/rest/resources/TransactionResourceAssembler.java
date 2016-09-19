package com.yungdu.us.squash.controllers.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.yungdu.us.squash.controllers.rest.TransactionController;
import com.yungdu.us.squash.data.Transaction;

@Component
public class TransactionResourceAssembler implements ResourceAssembler<Transaction, TransactionResource>
{

	@Override
	public TransactionResource toResource(final Transaction transaction)
	{
		// create the resource
		final TransactionResource transactionResource = new TransactionResource(transaction);

		// TODO
//		transactionResource.add(linkTo(
//				methodOn(TransactionController.class).getTransaction(transaction.getTransactionId()))
//				.withSelfRel());

		return transactionResource;
	}

}
