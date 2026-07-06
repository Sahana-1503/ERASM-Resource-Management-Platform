package com.erasm.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void testGenerateToken() {
        String token = jwtService.generateToken("admin@gmail.com");
        assertNotNull(token);
    }

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken("admin@gmail.com");
        String username = jwtService.extractUsername(token);
        assertEquals("admin@gmail.com", username);
    }

    @Test
    void testIsTokenValid() {
        String token = jwtService.generateToken("admin@gmail.com");
        assertTrue(jwtService.isTokenValid(token, "admin@gmail.com"));
    }

    @Test
    void testInvalidUsername() {
        String token = jwtService.generateToken("admin@gmail.com");
        assertFalse(jwtService.isTokenValid(token, "user@gmail.com"));
    }
}