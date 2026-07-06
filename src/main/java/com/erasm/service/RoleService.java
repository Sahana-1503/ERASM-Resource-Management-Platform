package com.erasm.service;

import java.util.List;

import com.erasm.dto.RoleDTO;

public interface RoleService {
	RoleDTO addRole(RoleDTO roleDTO);
	RoleDTO getRoleById(int roleId);
	List<RoleDTO> getAllRoles();
	RoleDTO updateRole(int roleId,RoleDTO roleDTO);
	void deleteRole(int roleId);

}
