package com.yungdu.us.commons;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.yungdu.us.commons.lang.DBC;

@Service
public class CollectionUtils
{

	/**
	 * Returns the first element of the given {@link Collection} that match the given {@link Predicate}.
	 *
	 * @param elmts the list to search into
	 * @param predicate the {@link Predicate} to apply to the elements of the {@link Collection}
	 * @return the first element that matched the given {@link Predicate}.
	 */
	public static <I> Optional<I> getFirstItemMatchingPredicate(final Collection<I> elmts, final Predicate<I> predicate)
	{
		DBC.checkNotNull(predicate);

		if (org.apache.commons.collections.CollectionUtils.isEmpty(elmts))
		{
			return Optional.empty();
		}

		for (final I elmt : elmts)
		{
			if (predicate.test(elmt))
			{
				return Optional.of(elmt);
			}
		}

		return Optional.empty();
	}

	/**
	 * Returns the first element of the given {@link Collection} that match the given {@link Predicate}.
	 *
	 * @param elmts the list to search into
	 * @param mapper function used to map the {@link Collection}'s element to an object that will be used in the {@link Predicate}
	 * @param predicate the {@link Predicate} to apply to the elements of the {@link Collection}
	 * @return the first element that matched the given predicate.
	 */
	public static <I, R> Optional<I> getFirstItemMatchingPredicate(final Collection<I> elmts, final Function<I, R> mapper,
			final Predicate<R> predicate)
	{
		DBC.checkNotNull(predicate);

		if (org.apache.commons.collections.CollectionUtils.isEmpty(elmts))
		{
			return Optional.empty();
		}

		for (final I elmt : elmts)
		{
			final R r = mapper.apply(elmt);
			if (predicate.test(r))
			{
				return Optional.of(elmt);
			}
		}

		return Optional.empty();
	}

	/**
	 * Checks that all elements in the {@link Collection} match the given {@link Predicate}.
	 *
	 * @param elmts the {@link Collection} to check
	 * @param predicate the {@link Predicate}
	 * @return {@code true} if all elements in the {@link Collection} match the {@link Predicate}. {@code false} otherwise.
	 */
	public static <I> boolean checkAllItemsMatchPredicate(final Collection<I> elmts, final Predicate<I> predicate)
	{
		DBC.checkNotNull(predicate);

		if (org.apache.commons.collections.CollectionUtils.isEmpty(elmts))
		{
			return true;
		}

		for (final I elmt : elmts)
		{
			if (!predicate.test(elmt))
			{
				return false;
			}
		}

		return true;
	}
}
