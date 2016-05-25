package com.aazsoft.mvc.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "User")
@NamedQueries(value = {
		@NamedQuery(name = "User.findAll", query = "select u from User u"),
		@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email= :email"),
		@NamedQuery(name = "User.findAllSortByEmail", query = "select u from User u order by u.email"), 
		@NamedQuery(name = "User.findById", query = "select u from User u where u.id= :id")})
public class User implements Serializable {

	private static final long serialVersionUID = 3293806819349409124L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", email='"
				+ email.replaceFirst("@.*", "@***") + ", passwordHash='"
				+ passwordHash.substring(0, 10) + ", role=" + role + '}';
	}
}
