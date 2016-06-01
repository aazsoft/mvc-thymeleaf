package com.aazsoft.mvc.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aazsoft.mvc.dao.entity.User;
import com.aazsoft.mvc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

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

}
