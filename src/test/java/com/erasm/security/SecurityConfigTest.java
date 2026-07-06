package com.erasm.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class SecurityConfigTest {

    private final JwtAuthenticatorFilter jwtFilter = mock(JwtAuthenticatorFilter.class);
    private final CustomUserDetailsService userDetailsService = mock(CustomUserDetailsService.class);

    private final SecurityConfig securityConfig =
            new SecurityConfig(jwtFilter, userDetailsService);

    @Test
    void testPasswordEncoder() {

        PasswordEncoder encoder = securityConfig.passwordEncoder();

        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void testAuthenticationProvider() {

        DaoAuthenticationProvider provider =
                securityConfig.authenticationProvider();

        assertNotNull(provider);
    }

    @Test
    void testAuthenticationManager() throws Exception {

        AuthenticationConfiguration configuration =
                mock(AuthenticationConfiguration.class);

        AuthenticationManager manager =
                mock(AuthenticationManager.class);

        when(configuration.getAuthenticationManager())
                .thenReturn(manager);

        AuthenticationManager result =
                securityConfig.authenticationManager(configuration);

        assertEquals(manager, result);
    }
}