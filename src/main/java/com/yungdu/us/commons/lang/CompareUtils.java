/**
 *
 */
package com.yungdu.us.commons.lang;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;


/**
 * Utility functions to compare certain core java types.
 */
public class CompareUtils
{
	public static boolean hasPriceValueChanged(final Double oldValue, final Double newValue)
	{
		if (oldValue == null)
		{
			return (newValue != null);
		}
		else if (newValue == null)
		{
			return true;
		}

		return (Math.abs(oldValue.doubleValue() - newValue.doubleValue()) > 0.001);
	}

	public static boolean hasValueChanged(final Object oldValue, final Object newValue)
	{
		if (oldValue == null)
		{
			return (newValue != null);
		}
		else if (newValue == null)
		{
			return true;
		}

		return !oldValue.equals(newValue);
	}

	public static boolean equalsDouble(final double value, final double target, final double tolerance)
	{
		return value >= target - tolerance && value <= target + tolerance;

	}

	public static boolean greaterDouble(final double value, final double target, final double tolerance)
	{
		return value > target + tolerance;
	}

	/**
	 * Returns true when an examined BigDecimal is equal to the specified <code>operand</code>, within a range of +/- 0.005. The comparison
	 * for equality is done by BigDecimals {@link java.math.BigDecimal#compareTo(java.math.BigDecimal)} method.
	 *
	 * @param examined the examined value
	 * @param operand the expected value of matching BigDecimals
	 * @return true, if examined is equal to or close to the operand
	 */
	public static boolean closeTo(final BigDecimal examined, final BigDecimal operand)
	{
		return closeTo(examined, operand, BigDecimal.valueOf(0.005));
	}

	/**
	 * Returns true when an examined BigDecimal is equal to the specified <code>operand</code>, within a range of +/- <code>tolerance</code>.
	 * The comparison for equality is done by BigDecimals {@link java.math.BigDecimal#compareTo(java.math.BigDecimal)} method.
	 *
	 * @param examined the examined value
	 * @param operand the expected value of matching BigDecimals
	 * @param tolerance the delta (+/-) within which matches will be allowed
	 * @return true, if examined is equal to or close to the operand
	 */
	public static boolean closeTo(final BigDecimal examined, final BigDecimal operand, final BigDecimal tolerance)
	{
		return examined.subtract(operand, MathContext.DECIMAL128).abs().subtract(tolerance, MathContext.DECIMAL128).stripTrailingZeros()
				.compareTo(BigDecimal.ZERO) <= 0;
	}

	public static <T> Set<T> findDuplicates(final Collection<T> list)
	{
		final Set<T> tmpSet = new HashSet<T>();
		final Set<T> retSet = new HashSet<T>();

		// Set#add returns false if the set does not change, which
		// indicates that a duplicate element has been added.
		for (final T item : list)
		{
			if (!tmpSet.add(item))
			{
				retSet.add(item);
			}
		}

		return retSet;
	}

	public static void checkEqual(final String str1, final String str2)
	{
		if (str1 == null)
		{
			if (str2 != null)
			{
				throw new IllegalArgumentException();
			}
			return;
		}
		else if (str2 == null)
		{
			throw new IllegalArgumentException();
		}

		if (!str1.trim().equals(str2.trim()))
		{
			throw new IllegalArgumentException();
		}
	}

	public static void checkEqual(final Object obj1, final Object obj2)
	{
		if (!ObjectUtils.equals(obj1, obj2))
		{
			throw new IllegalArgumentException();
		}
	}
}
