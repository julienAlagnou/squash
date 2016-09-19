package com.yungdu.us.squash.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.yungdu.us.commons.lang.DBC;
import com.yungdu.us.squash.dao.GenericDao;
import com.yungdu.us.squash.dao.repositories.UserRepository;
import com.yungdu.us.squash.data.Account;
import com.yungdu.us.squash.data.User;
import com.yungdu.us.squash.utils.exceptions.ElementAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GenericDao genericDao;

	@Override
	public User createUser(User user)
	{
		Preconditions.checkNotNull(user);
		DBC.checkNotBlank(user.getUserId(), "user id is missing.");

		// check user doesn't exist already
		findUserByUserId(user.getUserId()).ifPresent(p -> {
					throw new ElementAlreadyExistsException("Unable to create user. User with ID (" + p.getUserId()
							+ ") already exists.");
		});

		// create the user
		final User newUser = new User();
		newUser.setUserId(user.getUserId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());

		// save user
		userRepository.save(newUser);

		LOG.info("Created user with ID: {}", newUser.getUserId());

		return newUser;
	}

	@Override
	public Page<User> findAllUsers(final Pageable pageable)
	{
		Preconditions.checkNotNull(pageable);

		final Page<User> user = userRepository.findAll(pageable);

		return user;
	}

	@Override
	public Optional<User> findUserByUserId(String userId)
	{
		Preconditions.checkNotNull(userId);

		return Optional.ofNullable(userRepository.findByUserId(userId));
	}

	@Override
	public	void save(User user)
	{
		Preconditions.checkNotNull(user);
		
		userRepository.save(user);
	}
	
	@Override
	public	void save(Collection<User> users)
	{
		Preconditions.checkNotNull(users);
		
		userRepository.save(users);
	}

	@Override
	public Optional<Account> getDefaultAccount(User user) {
		Preconditions.checkNotNull(user);
		
		if (CollectionUtils.isEmpty(user.getAccounts()))
		{
			Account account = new Account();
			// FIXME
			account.setId("default");
			user.setAccounts(Collections.singletonList(account));
		}
		
		return Optional.of(user.getAccounts().get(0));
	}
}
