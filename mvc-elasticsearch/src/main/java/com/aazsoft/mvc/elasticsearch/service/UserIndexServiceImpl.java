package com.aazsoft.mvc.elasticsearch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aazsoft.mvc.dao.entity.User;
import com.aazsoft.mvc.elasticsearch.docs.UserDocument;
import com.aazsoft.mvc.elasticsearch.repos.UserESRepository;

@Service
@Transactional
public class UserIndexServiceImpl implements UserIndexService {

	@Autowired
	private UserESRepository userESRepo;
	
	@Override
	public void indexAllUsers(List<User> users) {
		List<UserDocument> userDocs = users.stream().map(u -> new UserDocument(
				u.getId(), u.getEmail(), u.getRole(), u.getAge(),u.getUsername()))
				.collect(Collectors.toList());
		userDocs.stream().forEach(u -> userESRepo.save(u));
	}

	@Override
	public void clearAllIndices() {
		userESRepo.deleteAll();
	}

}
