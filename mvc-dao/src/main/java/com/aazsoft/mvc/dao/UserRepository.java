package com.aazsoft.mvc.dao;

import java.util.Optional;

import com.aazsoft.mvc.dao.entity.User;

public interface UserRepository {

	Optional<User> findOneByEmail(String email);

}
