package com.aazsoft.mvc.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aazsoft.mvc.dao.entity.User;
import com.aazsoft.mvc.elasticsearch.docs.UserDocument;
import com.aazsoft.mvc.elasticsearch.service.UserIndexService;
import com.aazsoft.mvc.elasticsearch.service.UserSearchService;
import com.aazsoft.mvc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserIndexService esIndexService;
	
	@Autowired
	private UserSearchService esSearchService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<User> searchUser() {
		LOG.debug("Searching user...");
		return new ArrayList<User>(userService.getAllUsers());
	}
	
//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public Resources<UserResource> searchUser() {
//		LOG.debug("Searching user...");
//		List<UserResource> userResourceList = userService.getAllUsers()
//				.stream()
//				.map(UserResource::new)
//				.collect(Collectors.toList());
//
//		return new Resources<UserResource>(userResourceList);
//	}
	
	@RequestMapping(value = "/elasticsearch/index", method = RequestMethod.POST, produces="application/json")
	public String esIndexAllUsers() {
		List<User> users = userService.getAllUsers();
		if (CollectionUtils.isEmpty(users)) {
			return "{\"message\": \"No users to index!\"}";
		}
		esIndexService.indexAllUsers(users);
		return "{\"message\": \"Indexing successfully!\"}";
	}
	
	@RequestMapping(value = "/elasticsearch/searchByEmail/{email:.+}", method = RequestMethod.POST)
	public List<UserDocument> esSearchUserByEmail(final @PathVariable String email) {
		LOG.debug("Search users by Email={}", email);
		List<UserDocument> users = esSearchService.searchAllUsersByEmail(email);
		return users;
	}
	
	@RequestMapping(value = "/elasticsearch/clearIndices", method = RequestMethod.POST, produces="application/json")
	public String esClearAllIndices() {
		try {
			esIndexService.clearAllIndices();
		} catch (final Exception ex) {
			return "{\"message\": \"Clearing Indices on Elastic Search error!\"}";
		}
		return "{\"message\": \"Clearing All Indices on Elastic Search successfully!\"}";
	}

}
