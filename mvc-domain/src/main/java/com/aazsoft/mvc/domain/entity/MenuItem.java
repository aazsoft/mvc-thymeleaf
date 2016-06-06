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
@Table(name="menu_item")
@NamedQuery(name="MenuItem.findAll", query="SELECT m FROM MenuItem m")
@Data
@Document(indexName = "menuItem", type = "MenuItem", shards = 1, replicas = 0, refreshInterval = "-1")
@ToString(of = { "id", "miDesc", "miName", "miParentId" })
public class MenuItem implements Serializable {

	private static final long serialVersionUID = 6877472413176489513L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	@Column(name="mi_desc")
	private String miDesc;

	@Column(name="mi_name")
	private String miName;

	@Column(name="mi_parent_id")
	private int miParentId;

	//bi-directional many-to-one association to Permission
	@OneToMany(mappedBy="menuItem")
	@Field( type = FieldType.Nested)
	private List<Permission> permissions;

}