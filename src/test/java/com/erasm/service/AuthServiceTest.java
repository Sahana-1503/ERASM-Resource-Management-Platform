package com.erasm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import com.erasm.dto.LoginRequestDTO;
import com.erasm.dto.LoginResponseDTO;
import com.erasm.security.JwtService;
import com.erasm.service.implementation.AuthServiceImple;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImple authService;

    @Test
    void testLogin() {

        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setEmail("sahana@gmail.com");
        dto.setPassword("password123");

        when(jwtService.generateToken(dto.getEmail()))
                .thenReturn("sample-jwt-token");

        LoginResponseDTO response = authService.login(dto);

        assertEquals("sample-jwt-token", response.getToken());

        verify(authenticationManager).authenticate(any());
        verify(jwtService).generateToken(dto.getEmail());
    }

    @Test
    void testLogout() {

        String result = authService.logout();

        assertEquals("Logged out successfully", result);
    }
}