package com.yungdu.us.squash.services;

import java.io.File;
import java.util.List;

import com.yungdu.us.squash.data.Transaction;

public interface CsvTransactionImpexStrategy {

	List<Transaction> parseFile(File csvFile);

}