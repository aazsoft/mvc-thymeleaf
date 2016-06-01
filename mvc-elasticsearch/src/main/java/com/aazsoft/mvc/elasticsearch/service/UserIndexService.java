package com.aazsoft.mvc.elasticsearch.service;

import java.util.List;

import com.aazsoft.mvc.dao.entity.User;

public interface UserIndexService {

	void indexAllUsers(List<User> users);

	void clearAllIndices();
}
