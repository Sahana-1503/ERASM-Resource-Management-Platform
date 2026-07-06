package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilizationDTOTest {

    @Test
    void testNoArgsConstructor() {
        UtilizationDTO dto = new UtilizationDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {

        UtilizationDTO dto = new UtilizationDTO(
                1,
                "Sahana",
                75.5,
                24.5);

        assertEquals(1, dto.getEmployeeId());
        assertEquals("Sahana", dto.getEmployeeName());
        assertEquals(75.5, dto.getBillablePercentage());
        assertEquals(24.5, dto.getBenchPercentage());
    }

    @Test
    void testSettersAndGetters() {

        UtilizationDTO dto = new UtilizationDTO();

        dto.setEmployeeId(2);
        dto.setEmployeeName("Rahul");
        dto.setBillablePercentage(80.0);
        dto.setBenchPercentage(20.0);

        assertEquals(2, dto.getEmployeeId());
        assertEquals("Rahul", dto.getEmployeeName());
        assertEquals(80.0, dto.getBillablePercentage());
        assertEquals(20.0, dto.getBenchPercentage());
    }
}