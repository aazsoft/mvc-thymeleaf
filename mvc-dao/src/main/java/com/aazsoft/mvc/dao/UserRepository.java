package com.aazsoft.mvc.dao;

import java.util.List;
import java.util.Optional;

import com.aazsoft.mvc.dao.entity.User;

public interface UserRepository {

	Optional<User> findOneByEmail(String email);

	List<User> findAllSortByEmail();

	User save(User user);

	Optional<User> findOneById(long id);

	void delete(Long id);

}
