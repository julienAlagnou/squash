package com.yungdu.us.squash.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yungdu.us.squash.data.Account;

public interface AccountService
{

	/**
	 * Create a account
	 * @param userId 
	 *
	 * @param account the incoming account to create
	 * @return
	 */
	Account createAccount(String userId, Account account);

	/**
	 * Retrieves all stored accounts.
	 * @param userId 
	 *
	 * @param pageable pagination parameters
	 * @return
	 */
	Page<Account> findAllAccounts(String userId, Pageable pageable);

	/**
	 * Retrieves the account with the given id.
	 *
	 * @param accountId the id of the account to look for
	 * @return
	 */
	Optional<Account> findAccountByAccountId(String userId, String accountId);

	/**
	 * Delete the account with the given id.
	 * 
	 * @param accountId the id of the account to delete
	 */
	void deleteAccount(String userId, String accountId);


}
