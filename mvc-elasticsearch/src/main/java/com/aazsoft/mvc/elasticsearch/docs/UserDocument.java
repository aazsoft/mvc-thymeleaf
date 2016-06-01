package com.aazsoft.mvc.elasticsearch.docs;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.aazsoft.mvc.dao.entity.Role;

@Document(indexName = "user", type = "User", shards = 1, replicas = 0, refreshInterval = "-1")
public class UserDocument {

	@Id
	private Long id;

	private String email;

	private Role role;

	private int age;

	private String username;

	public UserDocument(){}
	
	public UserDocument(Long id, String email, Role role, int age,
			String username) {
		super();
		this.id = id;
		this.email = email;
		this.role = role;
		this.age = age;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
