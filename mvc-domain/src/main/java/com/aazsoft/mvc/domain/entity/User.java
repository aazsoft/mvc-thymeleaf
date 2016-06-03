package com.aazsoft.mvc.domain.entity;

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

import lombok.Data;

import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "User")
@NamedQueries(value = {
		@NamedQuery(name = "User.findAll", query = "select u from User u"),
		@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email= :email"),
		@NamedQuery(name = "User.findAllSortByEmail", query = "select u from User u order by u.email"),
		@NamedQuery(name = "User.findById", query = "select u from User u where u.id= :id") })
@Document(indexName = "user", type = "User", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
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

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

}
