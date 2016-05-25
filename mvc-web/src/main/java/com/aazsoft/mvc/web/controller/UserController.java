package com.aazsoft.mvc.web.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aazsoft.mvc.service.UserService;

@Controller
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
	@RequestMapping("/user/{id}")
	public ModelAndView getUserPage(@PathVariable Long id) {
		LOG.debug("Getting user page for user={}", id);
		return new ModelAndView("user", "user", userService.getUserById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
	}

}
