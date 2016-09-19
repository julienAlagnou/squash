package com.yungdu.us.squash.services;


import java.io.File;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.yungdu.us.squash.data.Transaction;
import com.yungdu.us.squash.dto.TransactionDTO;
import com.yungdu.us.squash.services.dto.TransactionLookupDTO;

public interface TransactionService
{

	/**
	 * Create a transaction
	 *
	 * @param transaction the incoming transaction to create
	 * @return
	 */
	Transaction createTransaction(Transaction transaction);

	/**
	 * Retrieves all stored transactions.
	 *
	 * @param pageable pagination parameters
	 * @return
	 */
	Page<Transaction> findAllTransactions(Pageable pageable);

	/**
	 * Retrieves the transaction with the given id.
	 *
	 * @param transactionId the id of the transaction to look for
	 * @return
	 */
	Optional<Transaction> findTransactionById(String transactionId);

	/**
	 * Delete the transaction with the given id.
	 * 
	 * @param transactionId the id of the transaction to delete
	 */
	void deleteTransaction(String transactionId);

	Page<Transaction> findTransactions(final String userId, final String accountId, TransactionLookupDTO transactionLookupDTO, Pageable pageable);

	/**
	 * Import transactions file.
	 * 
	 * @param file the file to import
	 */
	void importTransactionFile(final String userId, File file);

}
