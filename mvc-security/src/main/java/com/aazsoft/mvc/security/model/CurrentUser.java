package com.aazsoft.mvc.security.model;

import java.util.List;

import org.springframework.security.core.authority.AuthorityUtils;

import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.domain.entity.UserRole;

public class CurrentUser extends
		org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1348543844898511600L;

	private User user;

	public CurrentUser(User user) {
		super(user.getEmail(), user.getPasswordHash(), 
				AuthorityUtils.createAuthorityList(user.getRoles()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

	public List<UserRole> getRoles() {
		return user.getUserRoles();
	}

	@Override
	public String toString() {
		return "CurrentUser{" + "user=" + user + "} " + super.toString();
	}
}
