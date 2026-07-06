package com.erasm.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.erasm.dto.RoleDTO;
import com.erasm.entity.Role;
import com.erasm.mapper.RoleMapper;
import com.erasm.repository.RoleRepository;
import com.erasm.service.RoleService;

@Service
public class RoleServiceImple implements RoleService {
	private final RoleRepository roleRepository;

	public RoleServiceImple(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
	public RoleDTO addRole(RoleDTO roleDTO) {
		Role role=RoleMapper.toEntity(roleDTO);
		Role savedRole=roleRepository.save(role);
		return RoleMapper.toDTO(savedRole);
	}
	
	@Override
	public RoleDTO getRoleById(int roleId) {
		Role role=roleRepository.findById(roleId).orElse(null);
		return RoleMapper.toDTO(role);
		
	}
	
	@Override
	public List<RoleDTO> getAllRoles(){
		return roleRepository.findAll()
				.stream()
				.map(RoleMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public RoleDTO updateRole(int roleId,RoleDTO roleDTO) {
		Role role=roleRepository.findById(roleId).orElse(null);
		if(role==null) {
			return null;
			
		}
		role.setRoleName(roleDTO.getRoleName());
		Role updateRole=roleRepository.save(role);
		return RoleMapper.toDTO(updateRole);
	}
	
	@Override
	public void deleteRole(int roleId) {
		roleRepository.deleteById(roleId);
	}

}
