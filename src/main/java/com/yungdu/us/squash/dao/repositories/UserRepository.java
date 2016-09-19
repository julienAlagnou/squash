package com.yungdu.us.squash.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yungdu.us.squash.data.User;

public interface UserRepository extends MongoRepository<User, String>
{
	@Query(value="{ 'userId' : ?0 }", fields="{ 'accounts' : 0}")
	User findByUserId(String userId);

	@Query(value="{ 'userId' : ?0, 'accounts._id' : ?1 }", fields="{ 'accounts' : 1}")
	User findByUserIdAndAccountId(String userId,String accountId);
}
