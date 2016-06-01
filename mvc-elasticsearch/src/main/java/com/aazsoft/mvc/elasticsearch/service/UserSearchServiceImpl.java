package com.aazsoft.mvc.elasticsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aazsoft.mvc.elasticsearch.docs.UserDocument;
import com.aazsoft.mvc.elasticsearch.repos.UserESRepository;

@Service
@Transactional
public class UserSearchServiceImpl implements UserSearchService {

	@Autowired
	private UserESRepository userESRepo;

	@Override
	public List<UserDocument> searchAllUsersByEmail(String email) {
		return userESRepo.findByEmail(email);
	}
	
}
