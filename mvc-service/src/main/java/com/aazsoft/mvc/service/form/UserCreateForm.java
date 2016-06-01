package com.aazsoft.mvc.service.form;

import com.aazsoft.mvc.dao.entity.Role;

public class UserCreateForm {

	private String email = "";

	private String password = "";

	private String passwordRepeated = "";

	private Role role = Role.USER;

	private String passwordHash;

	private String username;

	private int age;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeated() {
		return passwordRepeated;
	}

	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserCreateForm{" + "email='"
				+ email.replaceFirst("@.+", "@***") + '\'' + ", password=***"
				+ '\'' + ", passwordRepeated=***" + '\'' + ", role=" + role
				+ '}';
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
