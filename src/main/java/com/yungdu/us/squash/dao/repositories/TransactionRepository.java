package com.yungdu.us.squash.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yungdu.us.squash.data.Account;
import com.yungdu.us.squash.data.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, String>
{
	
	Transaction findByTransactionId(String transactionId);
	
	Page<Transaction> findByAccount(Account account, Pageable pageable);
	
}
