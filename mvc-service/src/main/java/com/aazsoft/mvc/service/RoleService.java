package com.aazsoft.mvc.service;

import java.util.List;

import com.aazsoft.mvc.domain.entity.Role;

public interface RoleService {

	List<Role> getAllRoles();
	
	List<Role> findAllRolesInIds(List<Integer> ids);
}
