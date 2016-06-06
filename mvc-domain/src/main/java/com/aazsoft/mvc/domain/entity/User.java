package com.aazsoft.mvc.domain.entity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "User")
@NamedQueries(value = {
		@NamedQuery(name = "User.findAll", query = "select u from User u"),
		@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email= :email"),
		@NamedQuery(name = "User.findAllSortByEmail", query = "select u from User u order by u.email"),
		@NamedQuery(name = "User.findById", query = "select u from User u where u.id= :id") })
@Document(indexName = "user", type = "User", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@ToString(of = { "id", "email", "passwordHash", "username", "roles" })
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

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@ManyToMany
	@JoinTable(
		name="user_role"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	@Field(type = FieldType.Nested)
	@JsonManagedReference
	private List<Role> roles;

	@JsonIgnore
	public String getRolesStr() {
		return getRoles().stream()
				.map(r -> r.getRoleName())
				.collect(Collectors.joining(", "));
	}
}