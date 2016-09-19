package com.yungdu.us.squash.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import com.google.common.base.Throwables;
import com.yungdu.us.squash.data.JiveStatus;
import com.yungdu.us.squash.data.Transaction;

@Component
public class ClearcheckBookCsvTransactionImpExStrategy implements CsvTransactionImpexStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(ClearcheckBookCsvTransactionImpExStrategy.class);

	private static final DateTimeFormatter TRANSACTION_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	private static final String TRANSACTION_JIVED_INPUT = "yes";
	
	@Override
	public List<Transaction> parseFile(File csvFile)
	{
		List<Transaction> transactions = new ArrayList<>();
		
		CSVParser parser = null;
		try {
			parser = CSVParser.parse(csvFile, StandardCharsets.US_ASCII, CSVFormat.DEFAULT);
			
			boolean firstLine = true;
			for (CSVRecord record : parser.getRecords())
			{
				if (firstLine)
				{
					// skip 1st line
					firstLine = false;
					continue;
				}
				
				Transaction transaction = new Transaction();
				transaction.setTransactionId(UUID.randomUUID().toString());
				try {
				    LocalDate date = LocalDate.parse(record.get(0), TRANSACTION_DATE_FORMATTER);
				    Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
					transaction.setDate(Date.from(instant));
					
				}
				catch (DateTimeParseException exc) {
					LOG.error("{} is not parsable!", record.get(0));
				    Throwables.propagate(exc);
				}
				
				transaction.setDescription(record.get(2));
				// TODO
				//transaction.setCategoryId(record.get(3));
				transaction.setAmount(record.get(1));
				boolean jived = TRANSACTION_JIVED_INPUT.equals(record.get(5));
				transaction.setJived(jived? JiveStatus.JIVED : JiveStatus.NOTJIVED);
				//TODO
				//transaction.setAccount(record.get(4));
				
				transactions.add(transaction);
			}
			
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		
		return transactions;
	}
}
