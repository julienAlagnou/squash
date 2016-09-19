package com.yungdu.us.squash.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericDao
{

	<T> Page<T> findAllByAttributes(Map<String, ? extends Object> params, Class<T> type, Pageable pageable);
}
