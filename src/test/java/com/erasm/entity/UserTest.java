package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void testNoArgsConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void testAllArgsConstructor() {

        Employee employee = new Employee();
        Role role = new Role(1, "ADMIN");

        User user = new User(
                1,
                "admin@gmail.com",
                "password123",
                employee,
                role);

        assertEquals(1, user.getUserid());
        assertEquals("admin@gmail.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(employee, user.getEmployee());
        assertEquals(role, user.getRole());
    }

    @Test
    void testSettersAndGetters() {

        User user = new User();

        Employee employee = new Employee();
        Role role = new Role(2, "EMPLOYEE");

        user.setUserid(10);
        user.setEmail("user@gmail.com");
        user.setPassword("secret123");
        user.setEmployee(employee);
        user.setRole(role);

        assertEquals(10, user.getUserid());
        assertEquals("user@gmail.com", user.getEmail());
        assertEquals("secret123", user.getPassword());
        assertEquals(employee, user.getEmployee());
        assertEquals(role, user.getRole());
    }
}