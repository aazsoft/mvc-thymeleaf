package com.aazsoft.mvc.security.model;

import org.springframework.security.core.authority.AuthorityUtils;

import com.aazsoft.mvc.dao.entity.Role;
import com.aazsoft.mvc.dao.entity.User;

public class CurrentUser extends
		org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1348543844898511600L;

	private User user;

	public CurrentUser(User user) {
		super(user.getEmail(), user.getPasswordHash(), AuthorityUtils
				.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

	public Role getRole() {
		return user.getRole();
	}

	@Override
	public String toString() {
		return "CurrentUser{" + "user=" + user + "} " + super.toString();
	}
}
