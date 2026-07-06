package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ResourceRequestDTOTest {

    @Test
    void testNoArgsConstructor() {
        ResourceRequestDTO dto = new ResourceRequestDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {

        ResourceRequestDTO dto = new ResourceRequestDTO(
                1,
                "Java",
                3,
                "SUBMITTED",
                101);

        assertEquals(1, dto.getRequestId());
        assertEquals("Java", dto.getRequiredSkill());
        assertEquals(3, dto.getRequiredCount());
        assertEquals("SUBMITTED", dto.getStatus());
        assertEquals(101, dto.getProjectId());
    }

    @Test
    void testSettersAndGetters() {

        ResourceRequestDTO dto = new ResourceRequestDTO();

        dto.setRequestId(2);
        dto.setRequiredSkill("Spring Boot");
        dto.setRequiredCount(5);
        dto.setStatus("APPROVED");
        dto.setProjectId(201);

        assertEquals(2, dto.getRequestId());
        assertEquals("Spring Boot", dto.getRequiredSkill());
        assertEquals(5, dto.getRequiredCount());
        assertEquals("APPROVED", dto.getStatus());
        assertEquals(201, dto.getProjectId());
    }
}