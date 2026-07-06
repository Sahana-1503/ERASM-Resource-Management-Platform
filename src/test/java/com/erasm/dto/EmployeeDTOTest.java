package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployeeDTOTest {

    @Test
    void testNoArgsConstructor() {
        EmployeeDTO dto = new EmployeeDTO();
        assertNotNull(dto);
    }

    @Test
    void testSettersAndGetters() {

        EmployeeDTO dto = new EmployeeDTO();

        dto.setEmployeeId(1);
        dto.setEmployeeName("Sahana");
        dto.setPhoneNumber("9876543210");
        dto.setDepartment("IT");
        dto.setDesignation("Developer");
        dto.setExperience(2.5);
        dto.setBenchStatus("Available");
        dto.setUserId(10);

        assertEquals(1, dto.getEmployeeId());
        assertEquals("Sahana", dto.getEmployeeName());
        assertEquals("9876543210", dto.getPhoneNumber());
        assertEquals("IT", dto.getDepartment());
        assertEquals("Developer", dto.getDesignation());
        assertEquals(2.5, dto.getExperience());
        assertEquals("Available", dto.getBenchStatus());
        assertEquals(10, dto.getUserId());
    }
}