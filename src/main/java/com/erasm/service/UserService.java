package com.erasm.service;

import java.util.List;

import com.erasm.dto.UserDTO;

public interface UserService {
	UserDTO registerUser(UserDTO userDTO);
	
	UserDTO updateUser(int userId,UserDTO userDTO);
	void deleteUser(int userId);
	UserDTO changePassword(int userId, String password);
	
	
	List<UserDTO> getAllUsers();
	UserDTO getUserById(int userId);
	

}
