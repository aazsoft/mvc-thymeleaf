package com.aazsoft.mvc.domain.forms;

import lombok.Data;

import com.aazsoft.mvc.domain.entity.Role;

@Data
public class UserCreateForm {

	private String email;

	private String password;

	private String passwordRepeated;

	private Role role = Role.USER;

	private String passwordHash;

	private String username;

	private int age;

}
