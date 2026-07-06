package com.erasm.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.erasm.response.ErrorResponse;

class ErrorResponseTest {

    @Test
    void testConstructorAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        ErrorResponse response =
                new ErrorResponse(now, 404, "NOT_FOUND", "User not found");

        assertEquals(now, response.getTimsestamp());
        assertEquals(404, response.getStatus());
        assertEquals("NOT_FOUND", response.getError());
        assertEquals("User not found", response.getMessage());
    }

    @Test
    void testSettersAndGetters() {

        ErrorResponse response = new ErrorResponse();

        LocalDateTime now = LocalDateTime.now();

        response.setTimsestamp(now);
        response.setStatus(500);
        response.setError("ERROR");
        response.setMessage("Internal Server Error");

        assertEquals(now, response.getTimsestamp());
        assertEquals(500, response.getStatus());
        assertEquals("ERROR", response.getError());
        assertEquals("Internal Server Error", response.getMessage());
    }
}