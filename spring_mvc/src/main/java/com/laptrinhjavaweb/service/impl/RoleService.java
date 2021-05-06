package com.laptrinhjavaweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.service.IRoleService;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
	@Override
	public Map<String,String> findAll() {
		Map<String,String> result =new HashMap<>();
		List<RoleEntity> entity =roleRepository.findAll();
		for (RoleEntity roleEntity : entity) {
			result.put(roleEntity.getCode(), roleEntity.getName());
		}
		
		return result;
	}

}
