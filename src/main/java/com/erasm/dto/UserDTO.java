package com.erasm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
	private int userId;
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid email format")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min=8,message="Password must contain atleast 8 characters")
	private String password;
	
	private int roleId;
	
	
	public UserDTO() {
		
	}


	public UserDTO(int userId,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must contain atleast 8 characters") String password,
			int roleId) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}

	