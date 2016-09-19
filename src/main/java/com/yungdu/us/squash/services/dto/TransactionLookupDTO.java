package com.yungdu.us.squash.services.dto;

import com.yungdu.us.squash.data.JiveStatus;


/**
 * Data bound to the order look up form
 */
public class TransactionLookupDTO
{

	private String transactionId;

	private String category;

	private String description;

	private JiveStatus jiveStatus;
	
	public TransactionLookupDTO()
	{

	}

	public String getId() {
		return transactionId;
	}

	public void setId(String id) {
		this.transactionId = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public JiveStatus getJiveStatus() {
		return jiveStatus;
	}

	public void setJiveStatus(JiveStatus jiveStatus) {
		this.jiveStatus = jiveStatus;
	}

}
