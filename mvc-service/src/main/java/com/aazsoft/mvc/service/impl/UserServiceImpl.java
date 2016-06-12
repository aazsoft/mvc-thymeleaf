package com.aazsoft.mvc.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aazsoft.mvc.dao.RoleRepository;
import com.aazsoft.mvc.dao.UserRepository;
import com.aazsoft.mvc.domain.entity.Role;
import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.domain.forms.UserCreateForm;
import com.aazsoft.mvc.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepo;

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
		return userRepository.findAll(new Sort("email"));
	}

	@Override
	public User create(UserCreateForm form) {
		User user = new User();
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		user.setPasswordHash(form.getPasswordHash());
		user.setRoles(CollectionUtils.isEmpty(form.getRoles())? Collections.emptyList() : buildUserRoles(form.getRoles()));
		user.setAge(form.getAge());
		return userRepository.save(user);
	}

	private List<Role> buildUserRoles(List<Integer> roles) {
		return roleRepo.findByIdIn(roles);
	}

	@Override
	public void deleteUser(Long id) {
		LOGGER.debug("Deleting user with ID={}", id);
		userRepository.delete(id);
	}

}
