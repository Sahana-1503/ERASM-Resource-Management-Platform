package com.erasm.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.erasm.entity.Role;
import com.erasm.entity.User;
import com.erasm.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService service;

    @Test
    void testLoadUserByUsername() {

        Role role = new Role();
        role.setRoleName("ADMIN");

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("password");
        user.setRole(role);

        when(userRepository.findByEmail("admin@gmail.com"))
                .thenReturn(Optional.of(user));

        UserDetails details = service.loadUserByUsername("admin@gmail.com");

        assertEquals("admin@gmail.com", details.getUsername());
        assertEquals("password", details.getPassword());
    }

    @Test
    void testUserNotFound() {

        when(userRepository.findByEmail("abc@gmail.com"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername("abc@gmail.com"));
    }
}