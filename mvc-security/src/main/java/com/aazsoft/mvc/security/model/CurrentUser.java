package com.aazsoft.mvc.security.model;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;

import com.aazsoft.mvc.domain.entity.Role;
import com.aazsoft.mvc.domain.entity.User;

public class CurrentUser extends
		org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1348543844898511600L;

	private User user;

	public CurrentUser(User user) {
		super(user.getEmail(), user.getPasswordHash(), 
				AuthorityUtils.createAuthorityList(
						user.getRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()).toArray(new String[] {})));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

	public List<Role> getRoles() {
		return user.getRoles();
	}

	@Override
	public String toString() {
		return "CurrentUser{" + "user=" + user + "} " + super.toString();
	}
	
	public boolean hasADMINRole() {
		return getRoles().stream().anyMatch(r -> StringUtils.equalsIgnoreCase("ADMIN", r.getRoleName()));
	}
}
