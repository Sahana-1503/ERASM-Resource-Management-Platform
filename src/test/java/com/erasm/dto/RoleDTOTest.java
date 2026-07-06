package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RoleDTOTest {

    @Test
    void testNoArgsConstructor() {
        RoleDTO dto = new RoleDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {

        RoleDTO dto = new RoleDTO(1, "ADMIN");

        assertEquals(1, dto.getRoleId());
        assertEquals("ADMIN", dto.getRoleName());
    }

    @Test
    void testSettersAndGetters() {

        RoleDTO dto = new RoleDTO();

        dto.setRoleId(2);
        dto.setRoleName("EMPLOYEE");

        assertEquals(2, dto.getRoleId());
        assertEquals("EMPLOYEE", dto.getRoleName());
    }

    @Test
    void testExtraMethods() {

        RoleDTO dto = new RoleDTO();

        assertEquals(0, dto.getCertificationId());
        assertNull(dto.getCertificationName());
        assertNull(dto.getIssuedBy());
    }
}