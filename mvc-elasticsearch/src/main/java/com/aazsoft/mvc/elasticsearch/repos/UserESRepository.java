package com.aazsoft.mvc.elasticsearch.repos;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.aazsoft.mvc.domain.entity.User;

public interface UserESRepository extends ElasticsearchRepository<User, Long> {

	public List<User> findByEmail(final String email);
	
}
