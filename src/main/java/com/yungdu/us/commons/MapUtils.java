package com.yungdu.us.commons;

import java.util.Map;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.yungdu.us.commons.lang.DBC;

@Service
public class MapUtils
{

	/**
	 * Checks that all value of the given {@link Map} match the given {@link Predicate}.
	 *
	 * @param elmts the {@link Map} to check
	 * @param predicate the {@link Predicate} to apply to each Map's values
	 * @return {@code true} if all value of the {@link Map} match the {@link Predicate}. {@code false} otherwise.
	 */
	public static <I> boolean checkAllValuesMatchPredicate(final Map<?, I> elmts, final Predicate<I> predicate)
	{
		DBC.checkNotNull(predicate);

		if (org.apache.commons.collections.MapUtils.isEmpty(elmts))
		{
			return true;
		}

		for (final I elmt : elmts.values())
		{
			if (!predicate.test(elmt))
			{
				return false;
			}
		}

		return true;
	}
}
