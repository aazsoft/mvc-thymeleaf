package com.aazsoft.mvc.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aazsoft.mvc.dao.UserRepository;
import com.aazsoft.mvc.dao.entity.Role;
import com.aazsoft.mvc.dao.entity.User;
import com.aazsoft.mvc.service.UserService;
import com.aazsoft.mvc.service.dto.UserCreateForm;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        User u = new User();
        u.setEmail("t@gmail.com");
        u.setPasswordHash("$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6");
        u.setRole(Role.ADMIN);
        return Optional.of(u);//Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        User u = new User();
        u.setEmail("t@gmail.com");
        u.setPasswordHash("$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6");
        u.setRole(Role.ADMIN);
        return Optional.of(u);//userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return Collections.emptyList();//userRepository.findAll(new Sort("email"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash("$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6"/*new BCryptPasswordEncoder().encode(form.getPassword())*/);
        user.setRole(form.getRole());
        return null;//userRepository.save(user);
    }

}
