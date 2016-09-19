package com.yungdu.us.squash.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yungdu.us.squash.data.Account;
import com.yungdu.us.squash.data.User;

public interface AccountRepository extends MongoRepository<Account, String>
{
	Account findById(String id);

}
