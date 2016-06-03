package com.aazsoft.mvc.domain.forms;

import lombok.Data;

@Data
public class UserSearchForm {

	private String username;

	private String email;

	private String role;

	private Integer age;

}
