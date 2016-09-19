package com.yungdu.us.squash.controllers.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yungdu.us.commons.lang.DBC;
import com.yungdu.us.squash.controllers.AbstractPageController;
import com.yungdu.us.squash.controllers.rest.resources.TransactionResourceAssembler;
import com.yungdu.us.squash.data.JiveStatus;
import com.yungdu.us.squash.data.Transaction;
import com.yungdu.us.squash.services.TransactionService;
import com.yungdu.us.squash.services.dto.TransactionLookupDTO;

/**
 * Controller managing the transaction lookup page
 */
@Controller
@RequestMapping(value = "/transaction-lookup")
public class TransactionPageController extends AbstractPageController
{

	private static String SEARCH_TRANSACTION_PAGE = "transactionSearchPage";
	private static String TRANSACTION_DETAIL_PAGE = "transactionDetailPage";

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionResourceAssembler transactionResourceAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public String displayTransactionLookupPage(@ModelAttribute("transactionLookupData") final TransactionLookupDTO transactionLookupData,
			final Model model, @PageableDefault(size = 5, page = 0) final Pageable pageable)
	{
		// add form: used to get input back
		model.addAttribute("transactionLookupData", transactionLookupData);

		// populate order statuses list
		final Map<String, String> jiveStatuses = new LinkedHashMap<>();
		jiveStatuses.put("", "--");
		for (final JiveStatus jiveStatus : JiveStatus.values())
		{
			jiveStatuses.put(jiveStatus.name(), jiveStatus.getCode());
		}
		model.addAttribute("jiveStatuses", jiveStatuses);

		return SEARCH_TRANSACTION_PAGE;
	}

	@RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
	public String displayTransactionDetailPage(@PathVariable("transactionId") final String transactionId, final Model model)
	{
		DBC.checkNotBlank(transactionId, "transactionId is blank.");

		final Transaction transaction = transactionService.findTransactionById(transactionId).get();

		model.addAttribute("transactionResource", transactionResourceAssembler.toResource(transaction));

		return TRANSACTION_DETAIL_PAGE;
	}

}
