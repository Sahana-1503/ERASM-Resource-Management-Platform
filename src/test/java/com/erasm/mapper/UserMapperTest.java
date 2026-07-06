package com.erasm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.erasm.dto.UserDTO;
import com.erasm.entity.User;

public class UserMapperTest {

    @Test
    void testToDTO() {
        User user = new User();
        user.setUserid(1);
        user.setEmail("admin@gmail.com");
        user.setPassword("pass");

        UserDTO dto = UserMapper.toDTO(user);

        assertEquals(1, dto.getUserId());
        assertEquals("admin@gmail.com", dto.getEmail());
        assertEquals("pass", dto.getPassword());
    }

    @Test
    void testToEntity() {
        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setEmail("admin@gmail.com");
        dto.setPassword("pass");

        User user = UserMapper.toEntity(dto);

        assertEquals(1, user.getUserid());
        assertEquals("admin@gmail.com", user.getEmail());
        assertEquals("pass", user.getPassword());
    }

    @Test
    void testNull() {
        assertNull(UserMapper.toDTO(null));
        assertNull(UserMapper.toEntity(null));
    }
}