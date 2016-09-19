package com.yungdu.us.squash.controllers.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yungdu.us.commons.lang.DBC;
import com.yungdu.us.squash.controllers.AbstractRestController;
import com.yungdu.us.squash.controllers.rest.resources.TransactionResource;
import com.yungdu.us.squash.controllers.rest.resources.TransactionResourceAssembler;
import com.yungdu.us.squash.data.Transaction;
import com.yungdu.us.squash.services.TransactionService;
import com.yungdu.us.squash.services.dto.TransactionLookupDTO;

@RestController
@RequestMapping(value = "/users/{userId}/accounts/{accountId}/transactions")
public class TransactionsController extends AbstractRestController
{

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionResourceAssembler resourceAssembler;
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TransactionResource createTransaction(@RequestBody final Transaction transaction)
	{
		DBC.checkNotNull(transaction);

		// save order to DB
		final Transaction newTransaction = transactionService.createTransaction(transaction);

		return resourceAssembler.toResource(newTransaction);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PagedResources<TransactionResource> findTransactions(@PathVariable final String userId, @PathVariable final String accountId, final TransactionLookupDTO transactionLookupDTO, 
			@PageableDefault(size = 5, page = 0) final Pageable page, final PagedResourcesAssembler<Transaction> assembler)
	{
		final Page<Transaction> transactionsPage = transactionService.findTransactions(userId, accountId, transactionLookupDTO, page);

		final Link selfLink = linkTo(methodOn(TransactionsController.class).findTransactions(userId, accountId, transactionLookupDTO, page, null)).withSelfRel();

		return assembler.toResource(transactionsPage, resourceAssembler, selfLink);
	}

	@RequestMapping(value="/import", method = RequestMethod.POST)
	public String importTransactions(@PathVariable final String userId)
	{

		// TODO
		transactionService.importTransactionFile(userId, new File("/Users/jalagnou/Tmp/Others/Squash/clearcheckbook.csv"));

		return "OK";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
