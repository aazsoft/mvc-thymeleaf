package com.aazsoft.mvc.domain.entity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Table(name = "User")
@NamedQueries(value = {
		@NamedQuery(name = "User.findAll", query = "select u from User u"),
		@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email= :email"),
		@NamedQuery(name = "User.findAllSortByEmail", query = "select u from User u order by u.email"),
		@NamedQuery(name = "User.findById", query = "select u from User u where u.id= :id") })
@Document(indexName = "user", type = "User", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@ToString(of = { "id", "email", "age", "username" })
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

	@OneToMany(mappedBy = "user")
	@Field( type = FieldType.Nested)
	private List<UserRole> userRoles;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	public String[] getRoles() {
		return getUserRoles().stream()
				.map(r -> r.getRole().getRoleName())
				.collect(Collectors.toList()).toArray(new String[] {});
	}

	public String getRolesStr() {
		return getUserRoles().stream()
				.map(r -> r.getRole().getRoleName())
				.collect(Collectors.joining(", "));
	}
}