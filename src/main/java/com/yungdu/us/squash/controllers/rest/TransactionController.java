package com.yungdu.us.squash.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yungdu.us.commons.lang.DBC;
import com.yungdu.us.squash.controllers.AbstractRestController;
import com.yungdu.us.squash.controllers.rest.resources.TransactionResource;
import com.yungdu.us.squash.controllers.rest.resources.TransactionResourceAssembler;
import com.yungdu.us.squash.data.Transaction;
import com.yungdu.us.squash.services.TransactionService;

@RestController
@RequestMapping(value = "/users/{userId}/accounts/{accountId}/transactions/{transactionId}")
public class TransactionController extends AbstractRestController
{

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public TransactionResource getTransaction(@PathVariable final String userId, @PathVariable final String accountId, @PathVariable final String transactionId)
	{
		DBC.checkNotBlank(transactionId, "transactionId is mandatory to create an order");

		Transaction transaction = transactionService.findTransactionById(transactionId).get();
		
		return resourceAssembler.toResource(transaction);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteTransaction(@PathVariable final String transactionId)
	{
		DBC.checkNotBlank(transactionId, "transactionId is mandatory to create an order");

		transactionService.deleteTransaction(transactionId);

	}

}
