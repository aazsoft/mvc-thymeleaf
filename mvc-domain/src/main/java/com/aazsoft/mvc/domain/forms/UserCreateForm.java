package com.aazsoft.mvc.domain.forms;

import java.util.List;

import lombok.Data;

@Data
public class UserCreateForm {

	private String email;

	private String password;

	private String passwordRepeated;

	private List<Integer> roles;

	private String passwordHash;

	private String username;

	private int age;

}
