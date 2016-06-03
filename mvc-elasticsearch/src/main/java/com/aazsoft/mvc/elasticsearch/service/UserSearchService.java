package com.aazsoft.mvc.elasticsearch.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.domain.forms.UserSearchForm;

public interface UserSearchService {

	List<User> searchAllUsersByEmail(final String email);
	
	Page<User> searchUsers(final UserSearchForm searchForm, final Pageable pageable);
}
