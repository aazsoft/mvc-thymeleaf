package com.aazsoft.mvc.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aazsoft.mvc.dao.UserRepository;
import com.aazsoft.mvc.dao.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	EntityManager em;

	@Override
	public Optional<User> findOneByEmail(String email) {
		TypedQuery<User> query = em
				.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
		return Optional.of(query.getSingleResult());
	}

}
