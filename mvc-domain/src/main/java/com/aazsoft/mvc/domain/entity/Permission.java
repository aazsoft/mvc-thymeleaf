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
@Table(name = "permission")
@NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p")
@Data
@Document(indexName = "permission", type = "Permission", shards = 1, replicas = 0, refreshInterval = "-1")
@ToString(of = { "id", "permissionDesc", "permissionName" })
public class Permission implements Serializable {

	private static final long serialVersionUID = 9082102944331427212L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	@Column(name = "permission_desc")
	private String permissionDesc;

	@Column(name = "permission_name")
	private String permissionName;

	// bi-directional many-to-one association to Role
	@ManyToOne
	@Field( type = FieldType.Nested)
	private Role role;

	// bi-directional many-to-one association to MenuItem
	@ManyToOne
	@Field( type = FieldType.Nested)
	private MenuItem menuItem;

}