package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AllocationDTOTest {

    @Test
    void testNoArgsConstructor() {
        AllocationDTO dto = new AllocationDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {

        AllocationDTO dto = new AllocationDTO(
                1,
                101,
                201,
                80,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31),
                "ALLOCATED");

        assertEquals(1, dto.getAllocationId());
        assertEquals(101, dto.getEmployeeId());
        assertEquals(201, dto.getProjectId());
        assertEquals(80, dto.getAllocationPercentage());
        assertEquals(LocalDate.of(2025, 1, 1), dto.getAllocationDate());
        assertEquals(LocalDate.of(2025, 12, 31), dto.getReleaseDate());
        assertEquals("ALLOCATED", dto.getStatus());
    }

    @Test
    void testSettersAndGetters() {

        AllocationDTO dto = new AllocationDTO();

        dto.setAllocationId(2);
        dto.setEmployeeId(10);
        dto.setProjectId(20);
        dto.setAllocationPercentage(60);
        dto.setAllocationDate(LocalDate.of(2025, 2, 1));
        dto.setReleaseDate(LocalDate.of(2025, 11, 30));
        dto.setStatus("ACTIVE");

        assertEquals(2, dto.getAllocationId());
        assertEquals(10, dto.getEmployeeId());
        assertEquals(20, dto.getProjectId());
        assertEquals(60, dto.getAllocationPercentage());
        assertEquals(LocalDate.of(2025, 2, 1), dto.getAllocationDate());
        assertEquals(LocalDate.of(2025, 11, 30), dto.getReleaseDate());
        assertEquals("ACTIVE", dto.getStatus());
    }
}