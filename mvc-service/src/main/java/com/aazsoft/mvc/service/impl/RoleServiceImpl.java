package com.aazsoft.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aazsoft.mvc.dao.RoleRepository;
import com.aazsoft.mvc.domain.entity.Role;
import com.aazsoft.mvc.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}

	
}
