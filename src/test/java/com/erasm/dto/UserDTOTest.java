package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserDTOTest {

    @Test
    void testNoArgsConstructor() {
        UserDTO dto = new UserDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {

        UserDTO dto = new UserDTO(
                1,
                "admin@gmail.com",
                "password123",
                2);

        assertEquals(1, dto.getUserId());
        assertEquals("admin@gmail.com", dto.getEmail());
        assertEquals("password123", dto.getPassword());
        assertEquals(2, dto.getRoleId());
    }

    @Test
    void testSettersAndGetters() {

        UserDTO dto = new UserDTO();

        dto.setUserId(10);
        dto.setEmail("user@gmail.com");
        dto.setPassword("secret123");
        dto.setRoleId(5);

        assertEquals(10, dto.getUserId());
        assertEquals("user@gmail.com", dto.getEmail());
        assertEquals("secret123", dto.getPassword());
        assertEquals(5, dto.getRoleId());
    }
}