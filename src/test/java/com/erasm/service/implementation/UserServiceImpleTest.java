package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.erasm.dto.UserDTO;
import com.erasm.entity.Role;
import com.erasm.entity.User;
import com.erasm.exception.RoleNotFoundException;
import com.erasm.exception.UserAlreadyExistsException;
import com.erasm.exception.UserNotFoundException;
import com.erasm.repository.RoleRepository;
import com.erasm.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImpleTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImple userService;

    private User user;
    private UserDTO dto;
    private Role role;

    @BeforeEach
    void setUp() {

        role = new Role();
        role.setRoleID(1);
        role.setRoleName("ADMIN");

        user = new User();
        user.setUserid(1);
        user.setEmail("admin@gmail.com");
        user.setPassword("encoded");
        user.setRole(role);

        dto = new UserDTO();
        dto.setUserId(1);
        dto.setEmail("admin@gmail.com");
        dto.setPassword("password123");
        dto.setRoleId(1);
    }

    @Test
    void testRegisterUser() {

        when(userRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(roleRepository.findById(1))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encoded");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserDTO result = userService.registerUser(dto);

        assertNotNull(result);
        assertEquals("admin@gmail.com", result.getEmail());
    }

    @Test
    void testRegisterUser_EmailExists() {

        when(userRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class,
                () -> userService.registerUser(dto));
    }

    @Test
    void testRegisterUser_RoleNotFound() {

        when(userRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(roleRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class,
                () -> userService.registerUser(dto));
    }

    @Test
    void testGetUserById() {

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1);

        assertEquals("admin@gmail.com", result.getEmail());
    }

    @Test
    void testGetUserById_NotFound() {

        when(userRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(1));
    }

    @Test
    void testGetAllUsers() {

        when(userRepository.findAll())
                .thenReturn(Arrays.asList(user));

        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void testUpdateUser() {

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encoded");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserDTO result = userService.updateUser(1, dto);

        assertEquals("admin@gmail.com", result.getEmail());
    }

    @Test
    void testUpdateUser_NotFound() {

        when(userRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(1, dto));
    }

    @Test
    void testDeleteUser() {

        when(userRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(userRepository).deleteById(1);

        userService.deleteUser(1);

        verify(userRepository).deleteById(1);
    }

    @Test
    void testDeleteUser_NotFound() {

        when(userRepository.existsById(1))
                .thenReturn(false);

        assertThrows(UserNotFoundException.class,
                () -> userService.deleteUser(1));
    }

    @Test
    void testChangePassword() {

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encoded");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserDTO result = userService.changePassword(1, "newPassword");

        assertNotNull(result);
    }

    @Test
    void testChangePassword_NotFound() {

        when(userRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.changePassword(1, "newPassword"));
    }
}