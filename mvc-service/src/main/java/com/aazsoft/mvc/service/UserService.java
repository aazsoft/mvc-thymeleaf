package com.aazsoft.mvc.service;

import java.util.Collection;
import java.util.Optional;

import com.aazsoft.mvc.dao.entity.User;
import com.aazsoft.mvc.service.dto.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
