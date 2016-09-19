package com.yungdu.us.squash.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yungdu.us.squash.data.Category;

public interface CategoryRepository extends MongoRepository<Category, String>
{
	Category findById(String id);

}
