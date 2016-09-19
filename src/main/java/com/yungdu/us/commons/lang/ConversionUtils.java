package com.yungdu.us.commons.lang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 */
public class ConversionUtils
{
	public static Integer[] wrap(final int... args)
	{
		final Integer[] ret = new Integer[args.length];
		for (int i = 0; i < args.length; i++)
		{
			ret[i] = Integer.valueOf(args[i]);
		}
		return ret;
	}

	public static List<String> toStringList(final List<? extends Object> lst)
	{
		final List<String> ret = new ArrayList<String>(lst.size());
		for (final Object object : lst)
		{
			ret.add(String.valueOf(object));
		}
		return ret;
	}

	/**
	 * @param val The value
	 * @return A new {@link BigDecimal} scaled to two and rounded.
	 */
	public static BigDecimal toCurrencyValue(final Double val)
	{
		return val == null ? toCurrencyValue(BigDecimal.ZERO) : toCurrencyValue(val.doubleValue());
	}

	/**
	 * @param val The value
	 * @return A new {@link BigDecimal} scaled to two and rounded.
	 */
	public static BigDecimal toCurrencyValue(final Long val)
	{
		return val == null ? toCurrencyValue(BigDecimal.ZERO) : toCurrencyValue(val.longValue());
	}

	/**
	 * @param val The value
	 * @return A new {@link BigDecimal} scaled to two and rounded.
	 */
	public static BigDecimal toCurrencyValue(final double val)
	{
		return BigDecimal.valueOf(val).setScale(2, getRoundingMode());
	}

	/**
	 * @param val The value
	 * @return A new {@link BigDecimal} scaled to two and rounded.
	 */
	public static BigDecimal toCurrencyValue(final BigDecimal val)
	{
		if (val == null)
		{
			return toCurrencyValue(BigDecimal.ZERO);
		}

		return val.setScale(2, getRoundingMode());
	}

	/**
	 * Safely converts the given string to a rounded double.
	 *
	 * @param val the value as a string
	 * @return the double (rounded, 0.00D if the string is empty)
	 */
	public static double toDoubleValue(final BigDecimal val)
	{
		final BigDecimal rounded = toCurrencyValue(val);
		return rounded.doubleValue();
	}

	/**
	 * Safely converts the given string to a rounded double.
	 *
	 * @param val the value as a string
	 * @return the double (rounded, 0.00D if the string is empty)
	 */
	public static double toDoubleValue(final Double val)
	{
		if (val == null)
		{
			return 0.00D;
		}

		return toDoubleValue(val.doubleValue());
	}

	/**
	 * Safely converts the given string to a rounded double.
	 *
	 * @param val the value as a string
	 * @return the double (rounded, 0.00D if the string is empty)
	 */
	public static double toDoubleValue(final double val)
	{
		return BigDecimal.valueOf(val).setScale(2, getRoundingMode()).doubleValue();
	}

	/**
	 * Safely converts the given string to a rounded double.
	 *
	 * @param val the value as a string
	 * @return the double (rounded, 0.00D if the string is empty)
	 */
	public static double toDoubleValue(final String val)
	{
		if (StringUtils.isBlank(val))
		{
			return 0.00D;
		}

		return new BigDecimal(val).setScale(2, getRoundingMode()).doubleValue();
	}

	public static RoundingMode getRoundingMode()
	{
		return RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Converts generic list of type M to a list of type L under the assumption that M items can be casted to L items.
	 *
	 * @param <M> the generic type
	 * @param <L> the generic type
	 * @param source the source
	 * @return the list
	 */
	public static <M extends Object, L extends Object> List<L> convertGenericList(final List<M> source)
	{
		final List<L> ret = new ArrayList<L>();
		for (final M sourceItem : source)
		{
			ret.add((L) sourceItem);
		}

		return ret;
	}

	public static List<String> toStringList(final Enum[] enumValues)
	{
		final List<String> str = new ArrayList<String>();
		for (final Enum val : enumValues)
		{
			str.add(val.toString());
		}
		return str;
	}

	public static ParameterizedType findParamType(final Type clazz)
	{
		if (clazz instanceof ParameterizedType)
		{
			return (ParameterizedType) clazz;
		}
		else if (clazz instanceof Class)
		{
			return findParamType(((Class) clazz).getGenericSuperclass());
		}

		return null;
	}

	public static Properties mapToProperties(final Map<String, String> map)
	{
		final Properties p = new Properties();
		final Set<Map.Entry<String, String>> set = map.entrySet();
		for (final Map.Entry<String, String> entry : set)
		{
			p.put(entry.getKey(), entry.getValue());
		}
		return p;
	}
}

