package com.erasm.security;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jakarta.servlet.ServletException;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticatorFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticatorFilter filter;

    @Test
    void testDoFilterWithoutAuthorizationHeader() throws ServletException, IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        verifyNoInteractions(jwtService);
    }

    @Test
    void testDoFilterWithValidToken() throws ServletException, IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer token123");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        when(jwtService.extractUsername("token123"))
                .thenReturn("admin@gmail.com");

        User user = new User(
                "admin@gmail.com",
                "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        when(userDetailsService.loadUserByUsername("admin@gmail.com"))
                .thenReturn(user);

        when(jwtService.isTokenValid("token123", "admin@gmail.com"))
                .thenReturn(true);

        filter.doFilter(request, response, chain);

        verify(jwtService).extractUsername("token123");
        verify(jwtService).isTokenValid("token123", "admin@gmail.com");
    }
}