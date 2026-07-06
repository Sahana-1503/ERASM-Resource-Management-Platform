package com.erasm.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleUserNotFound() {

        ResponseEntity<ApiError> response =
                handler.handleUserNotFound(
                        new UserNotFoundException("User not found"));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found",
                response.getBody().getMessage());
    }

    @Test
    void testHandleSkillNotFound() {

        ResponseEntity<ApiError> response =
                handler.handleSkillNotFound(
                        new SkillNotFound("Skill not found"));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testHandleAllocationException() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleAllocationException(
                        new AllocationException("Allocation failed"));

        assertEquals(HttpStatus.BAD_REQUEST,
                response.getStatusCode());
    }

    @Test
    void testHandleException() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleException(
                        new Exception("Internal Error"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode());
    }

    @Test
    void testHandleUserAlreadyExists() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleUserAlreadyExists(
                        new UserAlreadyExistsException("Already exists"));

        assertEquals(HttpStatus.CONFLICT,
                response.getStatusCode());
    }

    @Test
    void testHandleRoleNotFound() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleRoleNotFound(
                        new RoleNotFoundException("Role not found"));

        assertEquals(HttpStatus.NOT_FOUND,
                response.getStatusCode());
    }

    @Test
    void testHandleEmployeeNotFound() {

        ResponseEntity<ApiError> response =
                handler.handleEmployeeNotFound(
                        new EmployeeNotFoundException("Employee not found"));

        assertEquals(HttpStatus.NOT_FOUND,
                response.getStatusCode());
    }

    @Test
    void testHandleEmployeeSkillNotFound() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleEmployeeSkillNotFound(
                        new EmployeeSkillNotFoundException("Skill not found"));

        assertEquals(HttpStatus.NOT_FOUND,
                response.getStatusCode());
    }

    @Test
    void testHandleProjectNotFound() {

        ResponseEntity<ApiError> response =
                handler.handleProjectNotFound(
                        new ProjectNotFound("Project not found"));

        assertEquals(HttpStatus.NOT_FOUND,
                response.getStatusCode());
    }

    @Test
    void testHandleResourceRequestNotFound() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleResourceRequestNotFoundException(
                        new ResourceRequestNotFoundException("Request not found"));

        assertEquals(HttpStatus.NOT_FOUND,
                response.getStatusCode());
    }

    @Test
    void testHandleSkillAlreadyExists() {

        ResponseEntity<?> response =
                handler.handleSkillAlreadyExists(
                        new SkillAlreadyExistsException("Already exists"));

        assertEquals(HttpStatus.CONFLICT,
                response.getStatusCode());
    }
}