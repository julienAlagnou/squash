package com.yungdu.us.squash.utils.exceptions;

public class ElementAlreadyExistsException extends RuntimeException
{

	private static final long serialVersionUID = 2821718069871774513L;

	/**
	 * Constructs a <code>ElementAlreadyExistsException</code> with <tt>null</tt> as its error message string.
	 */
	public ElementAlreadyExistsException()
	{
	}

	/**
	 * Constructs a <code>ElementAlreadyExistsException</code>, saving a reference to the error message string <tt>s</tt> for later
	 * retrieval by the <tt>getMessage</tt> method.
	 *
	 * @param s the detail message.
	 */
	public ElementAlreadyExistsException(final String message)
	{
		super(message);
	}

}
