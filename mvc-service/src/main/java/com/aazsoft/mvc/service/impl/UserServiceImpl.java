package com.aazsoft.mvc.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aazsoft.mvc.dao.UserRepository;
import com.aazsoft.mvc.dao.entity.User;
import com.aazsoft.mvc.service.UserService;
import com.aazsoft.mvc.service.form.UserCreateForm;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserById(long id) {
		LOGGER.debug("Getting user={}", id);
		return userRepository.findOneById(id);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		LOGGER.debug("Getting user by email={}",
				email.replaceFirst("@.*", "@***"));
		return userRepository.findOneByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		LOGGER.debug("Getting all users");
		return userRepository.findAllSortByEmail();
	}

	@Override
	public User create(UserCreateForm form) {
		User user = new User();
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		user.setPasswordHash(form.getPasswordHash());
		user.setRole(form.getRole());
		user.setAge(form.getAge());
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		LOGGER.debug("Deleting user with ID={}", id);
		userRepository.delete(id);
	}

}
