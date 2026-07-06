package com.erasm.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erasm.dto.UserDTO;
import com.erasm.entity.Role;
import com.erasm.entity.User;
import com.erasm.exception.RoleNotFoundException;
import com.erasm.exception.UserAlreadyExistsException;
import com.erasm.exception.UserNotFoundException;
import com.erasm.mapper.UserMapper;
import com.erasm.repository.RoleRepository;
import com.erasm.repository.UserRepository;
import com.erasm.service.UserService;

@Service
public class UserServiceImple implements UserService{
	private final UserRepository userRepository;
	private static final Logger logger=LoggerFactory.getLogger(UserServiceImple.class);
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;


	
	
	
	public UserServiceImple(UserRepository userRepository, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException(
					"Email already exists:"+userDTO.getEmail());
			
		}
		User user=UserMapper.toEntity(userDTO);
		Role role=roleRepository.findById(userDTO.getRoleId())
				.orElseThrow(()->
				new RoleNotFoundException("Role not found"));
		user.setRole(role);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User savedUser=userRepository.save(user);
		logger.info("User created successfully with ID:{}",savedUser.getUserid());
		return UserMapper.toDTO(savedUser);
	}
	
	@Override 
	public UserDTO getUserById(int userId) {
		User user=userRepository.findById(userId)
				.orElseThrow(()->new UserNotFoundException(
						"User not found with id:"+userId));
		return UserMapper.toDTO(user);
				
	}
	@Override
	public List<UserDTO> getAllUsers(){
		return userRepository.findAll()
				.stream()
				.map(UserMapper::toDTO)
				.toList()
;
	}

	
	@Override
	public UserDTO updateUser(int userId, UserDTO userDTO) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId)
				.orElseThrow(()->new UserNotFoundException("User not found with id:"+userId));
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User updatedUser=userRepository.save(user);
		logger.info("User updated successfully with ID:{}",updatedUser.getUserid());
		
		
		
		return UserMapper.toDTO(updatedUser);
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		if(!userRepository.existsById(userId)) {
			logger.warn("Employee not found with ID:{}",userId);
			throw new UserNotFoundException("User not found with id:"+userId);
		}
		userRepository.deleteById(userId);
		logger.info("User deleted successfully with ID:{}",userId);
		

		
	}

	@Override
	public UserDTO changePassword(int userId, String password) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId)
				.orElseThrow(()->
						new UserNotFoundException("User not found with id:"+userId));
		user.setPassword(passwordEncoder.encode(password));
		User updatedUser=userRepository.save(user);
		
		
		
		
		return UserMapper.toDTO(updatedUser);
	}

	

}
