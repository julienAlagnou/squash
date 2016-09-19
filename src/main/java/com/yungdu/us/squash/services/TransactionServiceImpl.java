package com.yungdu.us.squash.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yungdu.us.commons.lang.DBC;
import com.yungdu.us.squash.dao.GenericDao;
import com.yungdu.us.squash.dao.MongoDaoStrategy;
import com.yungdu.us.squash.dao.repositories.TransactionRepository;
import com.yungdu.us.squash.data.Account;
import com.yungdu.us.squash.data.Category;
import com.yungdu.us.squash.data.Transaction;
import com.yungdu.us.squash.data.User;
import com.yungdu.us.squash.services.dto.TransactionLookupDTO;
import com.yungdu.us.squash.utils.exceptions.ElementAlreadyExistsException;

@Service
public class TransactionServiceImpl implements TransactionService
{
	private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private CsvTransactionImpexStrategy CsvTransactionImpexStrategy;
	
	@Override
	public Transaction createTransaction(Transaction transaction)
	{
		DBC.checkNotNull(transaction);
		DBC.checkNotBlank(transaction.getTransactionId(), "transaction id is missing.");
		DBC.checkNotBlank(transaction.getAmount(), "amount is missing.");

		// check transaction doesn't exist already
		findTransactionById(transaction.getTransactionId()).ifPresent(p -> {
					throw new ElementAlreadyExistsException("Unable to create transaction. Transaction with ID (" + p.getTransactionId()
							+ ") already exists.");
		});

		// create the Transaction
		final Transaction newTransaction = new Transaction();
		newTransaction.setTransactionId(transaction.getTransactionId());
		newTransaction.setDescription(transaction.getDescription());
		newTransaction.setAmount(transaction.getAmount());
		
		// set category
		Optional<Category> categoryOpt = categoryService.findCategoryById(transaction.getCategory().getId());
		if (categoryOpt.isPresent())
		{
			newTransaction.setCategory(categoryOpt.get());
		}

		// save transaction
		transactionRepository.save(newTransaction);

		LOG.info("Created transaction with ID: {}", newTransaction.getTransactionId());

		return newTransaction;
	}
	
	@Override
	public void deleteTransaction(String transactionId)
	{
		DBC.checkNotBlank(transactionId, "transactionId is blank");

		// check transaction doesn't exist already
		Transaction transaction = findTransactionById(transactionId).get();

		// delete transaction
		transactionRepository.delete(transaction);

		LOG.info("Deleted transaction with ID: {}", transactionId);
	}

	@Override
	public Page<Transaction> findAllTransactions(final Pageable pageable)
	{
		DBC.checkNotNull(pageable);

		final Page<Transaction> transaction = transactionRepository.findAll(pageable);

		return transaction;
	}


	@Override
	public Optional<Transaction> findTransactionById(String transactionId)
	{
		DBC.checkNotNull(transactionId);

		return Optional.ofNullable(transactionRepository.findByTransactionId(transactionId));
	}

	@Override
	public Page<Transaction> findTransactions(final String userId, final String accountId, final TransactionLookupDTO transactionLookupDTO, final Pageable pageable)
	{
		DBC.checkNotBlank(userId, "userId is blank");
		DBC.checkNotBlank(accountId, "accountId is blank");
		DBC.checkNotNull(pageable);
		DBC.checkNotNull(transactionLookupDTO);

		Account account = accountService.findAccountByAccountId(userId, accountId).orElseThrow(() -> new IllegalStateException("Account (userId: " + userId + ", accountId: " + accountId + ") doesn't exist."));
		
		// otherwise, build attribute map
		final Map<String, Object> params = new HashMap<>();

		if (StringUtils.isNotBlank(transactionLookupDTO.getTransactionId()))
		{
			params.put("transactionId", transactionLookupDTO.getTransactionId());
		}

		if (transactionLookupDTO.getJiveStatus() != null)
		{
			params.put("jived", transactionLookupDTO.getJiveStatus());
		}

		return genericDao.findAllByAttributes(params, Transaction.class, pageable);
	}

	
	@Override
	public void importTransactionFile(final String userId, File file) {
		
		User user = userService.findUserByUserId(userId).orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " doesn't exist."));
		
		// TODO temporary use of default account
		Optional<Account> account = userService.getDefaultAccount(user);
		
		if (!account.isPresent())
		{
			LOG.info("No account to import transactions");
			return;
		}
		
		List<Transaction> transactions = CsvTransactionImpexStrategy.parseFile(file);
		
		if (CollectionUtils.isEmpty(transactions))
		{
			LOG.info("No transaction found in file");
			return;
		}
		
		// TODO set account on transaction: for now use default account on all transaction
		for(Transaction transaction : transactions)
		{
			transaction.setAccount(account.get());
		}
		
		userService.save(user);
		transactionRepository.save(transactions);
	}
	
	
}
