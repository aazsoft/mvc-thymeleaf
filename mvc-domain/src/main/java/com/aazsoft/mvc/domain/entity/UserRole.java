package com.aazsoft.mvc.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Table(name = "user_role")
@NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u")
@Data
@Document(indexName = "userRole", type = "UserRole", shards = 1, replicas = 0, refreshInterval = "-1")
@ToString(of = { "id", "user", "role" })
public class UserRole implements Serializable {

	private static final long serialVersionUID = -4416906955255115140L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	// bi-directional many-to-one association to User
	@ManyToOne
	@Field( type = FieldType.Nested)
	private User user;

	// bi-directional many-to-one association to Role
	@ManyToOne
	@Field( type = FieldType.Nested)
	private Role role;

}