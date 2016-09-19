package com.yungdu.us.squash.data;


/**
 * Transaction types.
 */
public enum TransactionType
{

	/**
	 * Transaction deposit type.
	 */
	DEPOSIT("DEPOSIT"),
	/**
	 * Transaction withdrawal type.
	 */
	WITHDRAWAL("WITHDRAWAL");

	/** The code of this enum.*/
	private final String code;

	/**
	 * Creates a new enum value for this enum type.
	 *
	 * @param code the enum value code
	 */
	private TransactionType(final String code)
	{
		this.code = code.intern();
	}

	/**
	 * Gets the code of this enum value.
	 *
	 * @return code of value
	 */
	public String getCode()
	{
		return this.code;
	}

}
