package com.yungdu.us.commons.lang;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;


/**
 * DBC is a utils class realizing the Design By Contract principle.
 * Use this methods to create expectations between the objects.
 *
 * @author denislutz
 */
public class DBC
{

	/**
	 * Ensures the truth of an expression involving one or more parameters to the calling method.
	 *
	 * @param expression a boolean expression
	 */
	public static void checkArgument(final boolean expression)
	{
		Preconditions.checkArgument(expression);
	}

	/**
	 * Ensures the truth of an expression involving one or more parameters to the calling method.
	 *
	 * @param expression a boolean expression
	 * @param errorMessage the exception message to use if the check fails; will be converted to a string using
	 *           {@link String#valueOf(Object)}
	 */
	public static void checkArgument(final boolean expression, @Nullable final Object errorMessage)
	{
		Preconditions.checkArgument(expression, errorMessage);
	}

	/**
	 * Ensures the truth of an expression involving one or more parameters to the calling method.
	 *
	 * @param expression a boolean expression
	 * @param errorMessageTemplate a template for the exception message should the check fail. The message is formed by
	 *           replacing each {@code %s} placeholder in the template with an argument. These are matched by position -
	 *           the first {@code %s} gets {@code errorMessageArgs[0]}, etc. Unmatched arguments will be appended to the
	 *           formatted message in square braces. Unmatched placeholders will be left as-is.
	 * @param errorMessageArgs the arguments to be substituted into the message template. Arguments are converted to
	 *           strings using {@link String#valueOf(Object)}. {@code errorMessageArgs} is null (don't let this happen)
	 */
	public static void checkArgument(final boolean expression, @Nullable final String errorMessageTemplate,
			@Nullable final Object... errorMessageArgs)
	{
		Preconditions.checkArgument(expression, errorMessageTemplate, errorMessageArgs);
	}

	/**
	 * Ensures the truth of an expression involving the state of the calling instance, but not involving any parameters
	 * to the calling method.
	 *
	 * @param expression a boolean expression
	 * @throws IllegalStateException if {@code expression} is false
	 */
	public static void checkState(final boolean expression)
	{
		Preconditions.checkState(expression);
	}

	/**
	 * Ensures the truth of an expression involving the state of the calling instance, but not involving any parameters
	 * to the calling method.
	 *
	 * @param expression a boolean expression
	 * @param errorMessage the exception message to use if the check fails; will be converted to a string using
	 *           {@link String#valueOf(Object)}
	 */
	public static void checkState(final boolean expression, @Nullable final Object errorMessage)
	{
		Preconditions.checkState(expression, errorMessage);
	}

	/**
	 * Ensures the truth of an expression involving the state of the calling instance, but not involving any parameters
	 * to the calling method.
	 *
	 * @param expression a boolean expression
	 * @param errorMessageTemplate a template for the exception message should the check fail. The message is formed by
	 *           replacing each {@code %s} placeholder in the template with an argument. These are matched by position -
	 *           the first {@code %s} gets {@code errorMessageArgs[0]}, etc. Unmatched arguments will be appended to the
	 *           formatted message in square braces. Unmatched placeholders will be left as-is.
	 * @param errorMessageArgs the arguments to be substituted into the message template. Arguments are converted to
	 *           strings using {@link String#valueOf(Object)}. {@code errorMessageArgs} is null (don't let this happen)
	 */
	public static void checkState(final boolean expression, @Nullable final String errorMessageTemplate,
			@Nullable final Object... errorMessageArgs)
	{
		Preconditions.checkState(expression, errorMessageTemplate, errorMessageArgs);
	}

	/**
	 * Ensures that an object reference passed as a parameter to the calling method is not null.
	 *
	 * @param <T> the generic type
	 * @param reference an object reference
	 * @return the non-null reference that was validated
	 */
	public static <T> T checkNotNull(final T reference)
	{
		return Preconditions.checkNotNull(reference);
	}

	/**
	 * Ensures that an object reference passed as a parameter to the calling method is not null.
	 *
	 * @param <T> the generic type
	 * @param reference an object reference
	 * @param errorMessage the exception message to use if the check fails; will be converted to a string using
	 * @return the non-null reference that was validated {@link String#valueOf(Object)}
	 */
	public static <T> T checkNotNull(final T reference, @Nullable final Object errorMessage)
	{
		return Preconditions.checkNotNull(reference, errorMessage);
	}

	/**
	 * Ensures that an object reference passed as a parameter to the calling method is not null.
	 *
	 * @param <T> the generic type
	 * @param reference an object reference
	 * @param errorMessageTemplate a template for the exception message should the check fail. The message is formed by
	 *           replacing each {@code %s} placeholder in the template with an argument. These are matched by position -
	 *           the first {@code %s} gets {@code errorMessageArgs[0]}, etc. Unmatched arguments will be appended to the
	 *           formatted message in square braces. Unmatched placeholders will be left as-is.
	 * @param errorMessageArgs the arguments to be substituted into the message template. Arguments are converted to
	 *           strings using {@link String#valueOf(Object)}.
	 * @return the non-null reference that was validated
	 */
	public static <T> T checkNotNull(final T reference, @Nullable final String errorMessageTemplate,
			@Nullable final Object... errorMessageArgs)
	{
		return Preconditions.checkNotNull(reference, errorMessageTemplate, errorMessageArgs);
	}

	/**
	 * Ensures that {@code index} specifies a valid <i>element</i> in an array, list or string of size {@code size}. An
	 * element index may range from zero, inclusive, to {@code size}, exclusive.
	 *
	 * @param index a user-supplied index identifying an element of an array, list or string
	 * @param size the size of that array, list or string
	 * @return the value of {@code index}
	 */
	public static int checkElementIndex(final int index, final int size)
	{
		return Preconditions.checkElementIndex(index, size);
	}

	/**
	 * Ensures that {@code index} specifies a valid <i>element</i> in an array, list or string of size {@code size}. An
	 * element index may range from zero, inclusive, to {@code size}, exclusive.
	 *
	 * @param index a user-supplied index identifying an element of an array, list or string
	 * @param size the size of that array, list or string
	 * @param desc the text to use to describe this index in an error message
	 * @return the value of {@code index}
	 */
	public static int checkElementIndex(final int index, final int size, @Nullable final String desc)
	{
		return Preconditions.checkElementIndex(index, size, desc);
	}

	/**
	 * Ensures that {@code index} specifies a valid <i>position</i> in an array, list or string of size {@code size}. A
	 * position index may range from zero to {@code size}, inclusive.
	 *
	 * @param index a user-supplied index identifying a position in an array, list or string
	 * @param size the size of that array, list or string
	 * @return the value of {@code index}
	 */
	public static int checkPositionIndex(final int index, final int size)
	{
		return Preconditions.checkPositionIndex(index, size);
	}

	/**
	 * Ensures that {@code index} specifies a valid <i>position</i> in an array, list or string of size {@code size}. A
	 * position index may range from zero to {@code size}, inclusive.
	 *
	 * @param index a user-supplied index identifying a position in an array, list or string
	 * @param size the size of that array, list or string
	 * @param desc the text to use to describe this index in an error message
	 * @return the value of {@code index}
	 */
	public static int checkPositionIndex(final int index, final int size, @Nullable final String desc)
	{
		return Preconditions.checkPositionIndex(index, size, desc);
	}

	/**
	 * Ensures that {@code start} and {@code end} specify a valid <i>positions</i> in an array, list or string of size.
	 *
	 * @param start a user-supplied index identifying a starting position in an array, list or string
	 * @param end a user-supplied index identifying a ending position in an array, list or string
	 * @param size the size of that array, list or string {@code size}, and are in order. A position index may range from
	 *           zero to {@code size}, inclusive.
	 */
	public static void checkPositionIndexes(final int start, final int end, final int size)
	{
		Preconditions.checkPositionIndexes(start, end, size);
	}

	/**
	 * Check not blank.
	 *
	 * @param value the value
	 * @param errorMessage the error message
	 */
	public static final void checkNotBlank(final String value, final String errorMessage)
	{
		Preconditions.checkArgument(StringUtils.isNotBlank(value), errorMessage);
	}

	/**
	 * Check not empty collection.
	 *
	 * @param param the param
	 * @param errorMessage the error message
	 */
	public static final void checkNotEmptyCollection(final Collection<?> param, final String errorMessage)
	{
		Preconditions.checkArgument(CollectionUtils.isNotEmpty(param), errorMessage);
	}

	/**
	 * Check not empty collection.
	 *
	 * @param param the param
	 * @param errorMessage the error message
	 */
	public static final void checkNotEmptyCollection(final Collection<?> param, final String errorMessage, final Object... errorMessageArgs)
	{
		Preconditions.checkArgument(CollectionUtils.isNotEmpty(param), errorMessage, errorMessageArgs);
	}

	/**
	 * Checks for null and then the size of the array.
	 *
	 * @param errorMessage The error message
	 * @param size The expected size
	 * @param arr The array
	 */
	public static final void checkSize(final String errorMessage, final int size, final Object... arr)
	{
		Preconditions.checkNotNull(arr, errorMessage);
		Preconditions.checkArgument(arr.length == size, errorMessage);
	}


	/**
	 * Check type.
	 *
	 * @param value the value
	 * @param validParameterType the valid parameter type
	 */
	public static final void checkType(final Object value, final Class<?> validParameterType)
	{
		if (value == null || !validParameterType.isInstance(value))
		{
			throw new IllegalArgumentException("Expected " + validParameterType.getName() + " but is " +
		            (value == null ? "null" : "of type " + value.getClass().getName()));
		}
	}

	/**
	 * Check not null and append to error message.
	 *
	 * @param attribute the attribute
	 * @param errorMessage the error message
	 * @param value the value
	 */
	public static void checkNotNullAndAppendToErrorMessage(final Object attribute,
	      final StringBuilder errorMessage, final Object value)
	{
		if (value == null)
		{
			errorMessage.append(attribute + " is required\n");
		}
	}

	/**
	 * Safely compares the values of two big decimals.
	 *
	 * @param message Optional error message to prepend
	 * @param actual The actual value
	 * @param expected The value to compare with
	 */
	public static void compareBigDecimalsValue(final String message, final BigDecimal actual, final BigDecimal expected)
	{
		compareBigDecimalsValue(message, expected, actual, null);
	}

	/**
	 * Safely compares the values of two big decimals.
	 *
	 * @param message Optional error message to prepend
	 * @param actual The actual value
	 * @param expected The value to compare with
	 * @param tolerance Optional tolerance
	 */
	public static void compareBigDecimalsValue(final String message, final BigDecimal actual, final BigDecimal expected, final BigDecimal tolerance)
	{
		if (expected == null)
		{
			if (actual == null)
			{
				return;
			}

			throw new IllegalArgumentException(message + " expected was null but actual is " + actual);
		}

		if (actual == null)
		{
			throw new IllegalArgumentException(message + " actual was null but expected was " + expected);
		}

		if (expected.compareTo(actual) == 0)
		{
			return; // equals
		}

		if (tolerance != null)
		{
			if (tolerance.compareTo(expected.subtract(actual).abs()) >= 0)
			{
				return; // tolerate
			}
		}

		throw new IllegalArgumentException(message + " expected was " + expected + " but actual is " + actual);
	}

	/**
	 * Safely compares the values of two doubles.
	 *
	 * @param message Optional error message to prepend
	 * @param actual The actual value
	 * @param expected The value to compare with
	 */
	public static void compareDoubleValue(final String message, final double actual, final double expected)
	{
		compareDoubleValue(message, actual, expected, 0.00);
	}

	/**
	 * Safely compares the values of two doubles.
	 *
	 * @param message Optional error message to prepend
	 * @param actual The actual value
	 * @param expected The value to compare with
	 * @param tolerance Optional tolerance
	 */
	public static void compareDoubleValue(final String message, final double actual, final double expected, final double tolerance)
	{
		if (!(expected >= actual - tolerance && expected <= actual + tolerance))
		{
			throw new IllegalArgumentException(message + " expected was " + expected + " but actual is " + actual);
		}
	}

	/**
	 * Safely compares the given two values by using the equals function.
	 *
	 * @param oldValue The old value (can be null)
	 * @param newValue The new value (can be null)
	 * @return Whether old and new value are equal
	 */
	public static boolean areEqual(final Object oldValue, final Object newValue)
	{
		return !CompareUtils.hasValueChanged(oldValue, newValue);
	}

	public static File mapEntryWithKeyMustPointToExistingFile(final String key, final Map<String, String> attr)
	{
		final String filePath = attr.get(key);
		checkNotBlank(filePath, "Map entry with key " + key + " not found!");
		final File file = new File(filePath);
		if (!file.exists())
		{
			throw new IllegalArgumentException("The file " + file.getAbsolutePath() + " does not exist!");
		}

		return file;
	}

	public static String mapEntryWithKeyMustNotBeBlank(final String key, final Map<String, String> attr)
	{
		final String str = attr.get(key);
		checkNotBlank(str, "Map entry with key " + key + " is blank!");
		return str;
	}

	public static boolean isTrue(final Boolean val)
	{
		return Boolean.TRUE.equals(val);
	}
}
