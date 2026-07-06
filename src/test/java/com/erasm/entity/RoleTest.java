package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    void testNoArgsConstructor() {
        Role role = new Role();
        assertNotNull(role);
    }

    @Test
    void testAllArgsConstructor() {
        Role role = new Role(1, "ADMIN");

        assertEquals(1, role.getRoleID());
        assertEquals("ADMIN", role.getRoleName());
    }

    @Test
    void testSettersAndGetters() {
        Role role = new Role();

        role.setRoleID(2);
        role.setRoleName("EMPLOYEE");

        assertEquals(2, role.getRoleID());
        assertEquals("EMPLOYEE", role.getRoleName());
    }
}