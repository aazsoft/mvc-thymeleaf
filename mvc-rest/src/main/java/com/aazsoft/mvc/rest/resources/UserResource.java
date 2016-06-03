package com.aazsoft.mvc.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.rest.controller.UserRestController;

public class UserResource extends ResourceSupport {

	private final User user;

	public UserResource(User user) {
		this.user = user;
		this.add(new Link("http://localhost:8090/", "user-uri"));
        this.add(linkTo(UserRestController.class, "user/search").withRel("users"));
        this.add(linkTo(UserRestController.class, "user/search").withSelfRel());
	}

	public User getUser() {
		return user;
	}

}