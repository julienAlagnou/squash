package com.yungdu.us.squash.services;


import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yungdu.us.squash.data.Account;
import com.yungdu.us.squash.data.User;

public interface UserService
{

	/**
	 * Create a user
	 *
	 * @param user the incoming user to create
	 * @return
	 */
	User createUser(User user);

	/**
	 * Retrieves all stored users.
	 *
	 * @param pageable pagination parameters
	 * @return
	 */
	Page<User> findAllUsers(Pageable pageable);

	/**
	 * Retrieves the user with the given id.
	 *
	 * @param userId the id of the user to look for
	 * @return
	 */
	Optional<User> findUserByUserId(String userId);

	/**
	 * Saves the given user.
	 *
	 * @param user the user to save
	 */
	void save(User user);
	
	/**
	 * Saves the given users.
	 *
	 * @param user the users to save
	 */
	void save(Collection<User> users);

	/**
	 * Returns first account for user.
	 * 
	 * @param user
	 * @return
	 */
	Optional<Account> getDefaultAccount(User user);

}
