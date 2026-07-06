package com.erasm.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.erasm.dto.UserDTO;
import com.erasm.entity.Role;
import com.erasm.entity.User;
import com.erasm.exception.RoleNotFoundException;
import com.erasm.exception.UserAlreadyExistsException;
import com.erasm.exception.UserNotFoundException;
import com.erasm.repository.RoleRepository;
import com.erasm.repository.UserRepository;
import com.erasm.service.implementation.UserServiceImple;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserServiceImple userService;
	
	@Test
	void testRegisterUser() {
		UserDTO dto=new UserDTO();
		dto.setEmail("sahana@gmail.com");
		dto.setPassword("password123");
		dto.setRoleId(1);
		
		Role role=new Role();
		role.setRoleID(1);
		
		User user=new User();
		user.setEmail("sahana@gmail.com");
		

		when(userRepository.findByEmail(dto.getEmail()))
				.thenReturn(Optional.empty());
		when(roleRepository.findById(1))
		.thenReturn(Optional.of(role));
		
		when(passwordEncoder.encode("password123"))
		.thenReturn("encoderPassword");
		
		when(userRepository.save(any(User.class)))
		.thenReturn(user);


		UserDTO result=userService.registerUser(dto);
		
		assertEquals("sahana@gmail.com",result.getEmail());
		verify(userRepository).save(any(User.class));
	}
	@Test 
	void testRegisterUserAlreadyExists() {
		User user=new User();
		user.setEmail("sahana@gnmail.com");
		
		UserDTO dto=new UserDTO();
		dto.setEmail("sahana@gnmail.com");

		when(userRepository.findByEmail(dto.getEmail()))
				.thenReturn(Optional.of(user));
		assertThrows(UserAlreadyExistsException.class,()->userService.registerUser(dto));
		


		
		
	}
	@Test
    void testRegisterUserRoleNotFound() {

        UserDTO dto = new UserDTO();
        dto.setEmail("abc@gmail.com");
        dto.setPassword("12345678");
        dto.setRoleId(10);

        when(userRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(roleRepository.findById(10))
                .thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class,
                () -> userService.registerUser(dto));
    }

    @Test
    void testGetUserById() {

        User user = new User();
        user.setUserid(1);
        user.setEmail("sahana@gmail.com");

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        UserDTO dto = userService.getUserById(1);

        assertEquals("sahana@gmail.com", dto.getEmail());

        verify(userRepository).findById(1);
    }

    @Test
    void testGetUserByIdNotFound() {

        when(userRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(1));
    }

    @Test
    void testGetAllUsers() {

        User u1 = new User();
        u1.setUserid(1);

        User u2 = new User();
        u2.setUserid(2);

        when(userRepository.findAll())
                .thenReturn(Arrays.asList(u1, u2));

        List<UserDTO> users = userService.getAllUsers();

        assertEquals(2, users.size());

        verify(userRepository).findAll();
    }

    @Test
    void testUpdateUser() {

        User user = new User();
        user.setUserid(1);
        user.setEmail("old@gmail.com");

        UserDTO dto = new UserDTO();
        dto.setEmail("new@gmail.com");
        dto.setPassword("password123");

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.encode("password123"))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserDTO result = userService.updateUser(1, dto);

        assertEquals("new@gmail.com", result.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void testDeleteUserNotFound() {

        when(userRepository.existsById(1))
                .thenReturn(false);

        assertThrows(UserNotFoundException.class,
                () -> userService.deleteUser(1));
    }

    @Test
    void testChangePassword() {

        User user = new User();
        user.setUserid(1);

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.encode("newpassword"))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserDTO dto = userService.changePassword(1, "newpassword");

        verify(userRepository).save(any(User.class));
    }
	
	
	

}
