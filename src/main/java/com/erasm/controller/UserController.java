package com.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.UserDTO;
import com.erasm.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService=userService;
		
	}

	

	@PostMapping("/register")
	
	public ResponseEntity<UserDTO> registerUser(@Valid@RequestBody UserDTO userDTO){
		UserDTO savedUser=userService.registerUser(userDTO);
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	

	
			@GetMapping
		public ResponseEntity<List<UserDTO>> getAllUsers(){
			return ResponseEntity.ok(userService.getAllUsers());
			
		}
		@GetMapping("/{id}")
		public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
			return ResponseEntity.ok(userService.getUserById(id));
		}
		@PutMapping("/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<UserDTO> updateUser(
			@PathVariable int id,
			@Valid@RequestBody UserDTO userDTO){
			return ResponseEntity.ok(userService.updateUser(id, userDTO));
			
		}
		
		
		@DeleteMapping("{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<String> deleteUser(@PathVariable int id){
			userService.deleteUser(id);
			return ResponseEntity.ok("user deleted successfully");
		}
		@PutMapping("/{id}/password")
		public ResponseEntity<UserDTO> changePassword(
			@PathVariable int id,
			@RequestParam String password){
				return ResponseEntity.ok(userService.changePassword(id, password));
						
			
		
		
		
		}
	}


