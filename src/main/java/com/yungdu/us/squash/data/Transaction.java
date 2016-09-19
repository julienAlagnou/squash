/**
 * 
 */
package com.yungdu.us.squash.data;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;



@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction
{
	@Id
	private String transactionId;

	private Date date;
	
	private String description;

	private String amount;
	
	private JiveStatus jived;

	@DBRef
	private Account account;
	
	@DBRef
	private Category category;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public JiveStatus getJived() {
		return jived;
	}

	public void setJived(JiveStatus jived) {
		this.jived = jived;
	}

}
