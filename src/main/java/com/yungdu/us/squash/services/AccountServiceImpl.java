package com.yungdu.us.squash.services;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.yungdu.us.commons.lang.DBC;
import com.yungdu.us.squash.dao.GenericDao;
import com.yungdu.us.squash.dao.repositories.AccountRepository;
import com.yungdu.us.squash.dao.repositories.UserRepository;
import com.yungdu.us.squash.data.Account;
import com.yungdu.us.squash.data.User;
import com.yungdu.us.squash.utils.exceptions.ElementAlreadyExistsException;

@Service
public class AccountServiceImpl implements AccountService
{
	private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository repository;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Account createAccount(final String userId, final Account account)
	{
		DBC.checkNotBlank(userId, "user id is missing.");
		Preconditions.checkNotNull(account);
		DBC.checkNotBlank(account.getId(), "account id is missing.");

		// check account doesn't exist already
		findAccountByAccountId(userId, account.getId()).ifPresent(p -> {
					throw new ElementAlreadyExistsException("Unable to create account. Account with ID (" + p.getId()
							+ ") already exists.");
		});

		// create the account
		final Account newAccount = new Account();
		newAccount.setId(account.getId());
		newAccount.setName(account.getName());
		newAccount.setDescription(account.getDescription());

		// save account
		repository.save(newAccount);

		LOG.info("Created account with ID: {}", newAccount.getId());

		return newAccount;
	}
	
	@Override
	public void deleteAccount(final String userId, final String accountId)
	{
		DBC.checkNotBlank(userId, "user id is missing.");
		Preconditions.checkArgument(StringUtils.isNotBlank(accountId), "accountId is blank");

		// check account doesn't exist already
		Account account = findAccountByAccountId(userId, accountId).get();

		// delete account
		repository.delete(account);

		LOG.info("Deleted account with ID: {}", accountId);
	}

	@Override
	public Page<Account> findAllAccounts(final String userId, final Pageable pageable)
	{
		DBC.checkNotBlank(userId, "user id is missing.");
		Preconditions.checkNotNull(pageable);

		final Page<Account> account = repository.findAll(pageable);

		return account;
	}

	@Override
	public Optional<Account> findAccountByAccountId(final String userId, final String accountId)
	{
		DBC.checkNotBlank(userId, "user id is missing.");
		Preconditions.checkNotNull(accountId);

		User user = userRepository.findByUserIdAndAccountId(userId, accountId);
		return user.getAccounts().stream().findFirst();
	}

}
