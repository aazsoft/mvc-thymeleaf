package com.aazsoft.mvc.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
@Data
@Document(indexName = "role", type = "Role", shards = 1, replicas = 0, refreshInterval = "-1")
@ToString(of = { "id", "roleName", "roleDescription" })
public class Role implements Serializable {

	private static final long serialVersionUID = -7683680631875730954L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	@Column(name = "role_description")
	private String roleDescription;

	@Column(name = "role_name")
	private String roleName;

	// bi-directional many-to-one association to Permission
	@OneToMany(mappedBy = "role")
	@Field( type = FieldType.Nested)
	private List<Permission> permissions;

	// bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy = "role")
	@Field( type = FieldType.Nested)
	private List<UserRole> userRoles;

}