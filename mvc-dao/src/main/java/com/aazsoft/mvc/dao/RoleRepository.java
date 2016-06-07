package com.aazsoft.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aazsoft.mvc.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	List<Role> findByIdIn(List<Integer> idList);

}
