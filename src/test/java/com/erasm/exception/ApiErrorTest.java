package com.erasm.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ApiErrorTest {

    @Test
    void testConstructorAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        ApiError error =
                new ApiError(now,404,"NOT_FOUND","User not found");

        assertEquals(now,error.getTimestamp());
        assertEquals(404,error.getStatus());
        assertEquals("NOT_FOUND",error.getError());
        assertEquals("User not found",error.getMessage());
    }

    @Test
    void testSetters() {

        ApiError error = new ApiError();

        LocalDateTime now = LocalDateTime.now();

        error.setTimestamp(now);
        error.setStatus(500);
        error.setError("ERROR");
        error.setMessage("Internal Error");

        assertEquals(now,error.getTimestamp());
        assertEquals(500,error.getStatus());
        assertEquals("ERROR",error.getError());
        assertEquals("Internal Error",error.getMessage());
    }
}