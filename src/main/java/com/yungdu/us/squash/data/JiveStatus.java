package com.yungdu.us.squash.data;


/**
 * Jived transaction status.
 */
public enum JiveStatus
{

	/**
	 * Jived Transaction status.
	 */
	JIVED("JIVED"),
	/**
	 * Not jived transaction status.
	 */
	NOTJIVED("NOTJIVED");

	/** The code of this enum.*/
	private final String code;

	/**
	 * Creates a new enum value for this enum type.
	 *
	 * @param code the enum value code
	 */
	private JiveStatus(final String code)
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
