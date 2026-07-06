package com.erasm.mapper;

import com.erasm.dto.UserDTO;
import com.erasm.entity.User;

public class UserMapper {
	public static UserDTO toDTO(User user) {
		if (user==null) {
			return null;
			
		}
		UserDTO dto=new UserDTO();
		dto.setUserId(user.getUserid());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		return dto;
		
	}
	public static User toEntity(UserDTO dto) {
		if(dto==null) {
			return null;
			
		}
		User user=new User();
		user.setUserid(dto.getUserId());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		return user;
	}

}
