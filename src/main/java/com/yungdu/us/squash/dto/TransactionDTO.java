/**
 * 
 */
package com.yungdu.us.squash.dto;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionDTO
{
	@Id
	private String transactionId;
	
	private LocalDate date;
	
	private String description;

	private String amount;
	
	private String categoryId;

	private boolean jived;
	
	private String account;
	
	public TransactionDTO()
	{
		super();
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isJived() {
		return jived;
	}

	public void setJived(boolean jived) {
		this.jived = jived;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
