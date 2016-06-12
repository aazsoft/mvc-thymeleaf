package com.aazsoft.mvc.rest.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.domain.forms.UserSearchForm;
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
		return userService.getAllUsers();
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
	
	@RequestMapping(value = "/elasticsearch/index", 
			method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
	public String esIndexAllUsers() {
		List<User> users = userService.getAllUsers();
		if (CollectionUtils.isEmpty(users)) {
			return "{\"message\": \"No users to index!\"}";
		}
		esIndexService.indexAllUsers(users);
		return "{\"message\": \"Indexing successfully!\"}";
	}
	
	@RequestMapping(value = "/elasticsearch/searchByEmail/{email:.+}", method = RequestMethod.POST)
	public List<User> esSearchUserByEmail(final @PathVariable String email) {
		LOG.debug("Search users by Email={}", email);
		List<User> users = esSearchService.searchAllUsersByEmail(email);
		return users;
	}
	
	@RequestMapping(value = "/elasticsearch/clearIndices",
			method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String esClearAllIndices() {
		try {
			esIndexService.clearAllIndices();
		} catch (final Exception ex) {
			return "{\"message\": \"Clearing Indices on Elastic Search error!\"}";
		}
		return "{\"message\": \"Clearing All Indices on Elastic Search successfully!\"}";
	}

	@RequestMapping(value = "/elasticsearch/searchUsers", 
			method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<User> esSearchUsers(@RequestBody final UserSearchForm userSearchForm) {
		LOG.debug("Search users by userSearchForm={}", userSearchForm);
		Page<User> users = esSearchService.searchUsers(userSearchForm, new PageRequest(0, 5000));
		return users;
	}
	
	@RequestMapping(value = "/bulkInsert", 
			method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String bulkInsert() {
		LOG.debug("Bulk insert {} users to DB", "1.5M");
		try {
			userService.bulkInsert(50000); // performance with 1.5M data!
			LOG.debug("Bulk insert {} users successful", "1.5M");
		} catch (final Exception ex) {
			return "{\"message\": \"Bulk insert users to DB error!\"}";
		}
		return "{\"message\": \"Bulk insert 1.5M users to DB successfully!\"}";
	}
	
}
