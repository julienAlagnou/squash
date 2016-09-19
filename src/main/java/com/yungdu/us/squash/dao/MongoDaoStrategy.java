package com.yungdu.us.squash.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mongo")
public class MongoDaoStrategy implements GenericDao
{
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public <T> Page<T> findAllByAttributes(final Map<String, ? extends Object> params, final Class<T> type, final Pageable pageable)
	{
		// build criteria
		final List<Criteria> criterias = new ArrayList<>();

		for (final Entry<String, ? extends Object> param : params.entrySet())
		{
			criterias.add(Criteria.where(param.getKey()).is(param.getValue()));
		}

		// build Query
		Criteria criteria = null;
		if (CollectionUtils.isNotEmpty(criterias))
		{
			criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
		}
		else
		{
			criteria = new Criteria();
		}
		final Query query = Query.query(criteria);

		// get total count for pagination details
		final long totalNumberOfRecords = mongoTemplate.count(query, type);

		// normalize pagination
		final Pageable returnedPageable = getNormalizedPageable(pageable, totalNumberOfRecords);

		// set up pagination on Query
		query.skip(returnedPageable.getPageNumber() * returnedPageable.getPageSize());
		query.limit(returnedPageable.getPageSize());

		// run query
		final List<T> objects = mongoTemplate.find(query, type);

		return new PageImpl<>(objects, returnedPageable, totalNumberOfRecords);
	}

	/**
	 * Returns a normalized {@link Pageable} given the total number of records returned by the query.
	 *
	 * @param pageable the pageable to consider
	 * @param totalNumberOfRecords the total number of records returned by the query.
	 * @return
	 */
	public static Pageable getNormalizedPageable(final Pageable pageable, final long totalNumberOfRecords)
	{
		final long totalNumberOfPage = (totalNumberOfRecords + pageable.getPageSize() - 1) / pageable.getPageSize();

		if (totalNumberOfPage == 0)
		{
			return new PageRequest(0, pageable.getPageSize());
		}
		else if (pageable.getPageNumber() > totalNumberOfPage - 1)
		{
			return new PageRequest((int) (totalNumberOfPage - 1), pageable.getPageSize());
		}
		else if (pageable.getPageNumber() < 0)
		{
			return new PageRequest(0, pageable.getPageSize());
		}

		return pageable;
	}
}
