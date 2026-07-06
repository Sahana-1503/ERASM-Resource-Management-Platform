package com.erasm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.RoleDTO;
import com.erasm.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	private final RoleService roleService;

		public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping
	public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO roleDTO){
		return ResponseEntity.ok(roleService.addRole(roleDTO));
		
		}
	
	@GetMapping
	public ResponseEntity<List<RoleDTO>> getAllRoles(){
		return ResponseEntity.ok(roleService.getAllRoles());
	}
	
	@GetMapping("/{id}")
	
	public ResponseEntity<RoleDTO> getRoleById(@PathVariable int id){
		return ResponseEntity.ok(roleService.getRoleById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RoleDTO> updateRole(
			@PathVariable int id,
			@RequestBody RoleDTO roleDTO){
		return ResponseEntity.ok(roleService.updateRole(id, roleDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable int id){
		roleService.deleteRole(id);
		return ResponseEntity.ok("Role deleted Successfully");
	}
	
	

}



