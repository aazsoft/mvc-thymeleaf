package com.aazsoft.mvc.service;

import java.util.List;
import java.util.Optional;

import com.aazsoft.mvc.dao.entity.User;
import com.aazsoft.mvc.service.form.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    User create(UserCreateForm form);

	void deleteUser(Long id);

}
