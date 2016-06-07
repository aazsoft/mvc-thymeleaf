package com.aazsoft.mvc.domain.forms;

import java.util.List;

import lombok.Data;

@Data
public class UserSearchForm {

	private String username;

	private String email;

	private List<Integer> roles;

	private Integer age;

}
