package com.erasm.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExceptionClassesTest {

    @Test
    void testUserNotFoundException() {
        UserNotFoundException ex = new UserNotFoundException("User not found");
        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testEmployeeNotFoundException() {
        EmployeeNotFoundException ex = new EmployeeNotFoundException("Employee not found");
        assertEquals("Employee not found", ex.getMessage());
    }

    @Test
    void testProjectNotFound() {
        ProjectNotFound ex = new ProjectNotFound("Project not found");
        assertEquals("Project not found", ex.getMessage());
    }

    @Test
    void testSkillNotFound() {
        SkillNotFound ex = new SkillNotFound("Skill not found");
        assertEquals("Skill not found", ex.getMessage());
    }

    @Test
    void testAllocationException() {
        AllocationException ex = new AllocationException("Allocation error");
        assertEquals("Allocation error", ex.getMessage());
    }

    @Test
    void testEmployeeSkillNotFoundException() {
        EmployeeSkillNotFoundException ex =
                new EmployeeSkillNotFoundException("Employee skill not found");

        assertEquals("Employee skill not found", ex.getMessage());
    }

    @Test
    void testResourceRequestNotFoundException() {
        ResourceRequestNotFoundException ex =
                new ResourceRequestNotFoundException("Request not found");

        assertEquals("Request not found", ex.getMessage());
    }

    @Test
    void testRoleNotFoundException() {
        RoleNotFoundException ex =
                new RoleNotFoundException("Role not found");

        assertEquals("Role not found", ex.getMessage());
    }

    @Test
    void testSkillAlreadyExistsException() {
        SkillAlreadyExistsException ex =
                new SkillAlreadyExistsException("Skill exists");

        assertEquals("Skill exists", ex.getMessage());
    }

    @Test
    void testUserAlreadyExistsException() {
        UserAlreadyExistsException ex =
                new UserAlreadyExistsException("User exists");

        assertEquals("User exists", ex.getMessage());
    }
}