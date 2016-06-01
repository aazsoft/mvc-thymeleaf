package com.aazsoft.mvc.dao.impl;

import java.util.List;
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

	@Override
	public List<User> findAllSortByEmail() {
		TypedQuery<User> query = em
				.createNamedQuery("User.findAllSortByEmail", User.class);
		return query.getResultList();
	}

	@Override
	public User save(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public Optional<User> findOneById(long id) {
		TypedQuery<User> query = em
				.createNamedQuery("User.findById", User.class);
		query.setParameter("id", id);
		return Optional.of(query.getSingleResult());
	}

	@Override
	public void delete(Long id) {
		em.remove(em.find(User.class, id));
	}

}
