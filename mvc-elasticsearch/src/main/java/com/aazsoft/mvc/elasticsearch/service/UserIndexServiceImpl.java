package com.aazsoft.mvc.elasticsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.elasticsearch.repos.UserESRepository;

@Service
@Transactional
public class UserIndexServiceImpl implements UserIndexService {

	@Autowired
	private UserESRepository userESRepo;
	
	@Autowired
    protected ElasticsearchTemplate esTemplate;
	
	@Override
	public void indexAllUsers(List<User> users) {
		users.stream().forEach(u -> userESRepo.save(u));
	}

	@Override
	public void clearAllIndices() {
		userESRepo.deleteAll();
	}

}
