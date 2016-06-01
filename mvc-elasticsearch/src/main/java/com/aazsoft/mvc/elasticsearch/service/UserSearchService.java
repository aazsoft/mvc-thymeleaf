package com.aazsoft.mvc.elasticsearch.service;

import java.util.List;

import com.aazsoft.mvc.elasticsearch.docs.UserDocument;

public interface UserSearchService {

	List<UserDocument> searchAllUsersByEmail(final String email);
}
