package com.aazsoft.mvc.service;

import java.util.List;
import java.util.Optional;

import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.domain.forms.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    User create(UserCreateForm form);

	void deleteUser(Long id);

	void bulkInsert(int d);

}
