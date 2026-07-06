package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployeeSkillDTOTest {

    @Test
    void testNoArgsConstructor() {
        EmployeeSkillDTO dto = new EmployeeSkillDTO();
        assertNotNull(dto);
    }

    @Test
    void testSettersAndGetters() {

        EmployeeSkillDTO dto = new EmployeeSkillDTO();

        dto.setEmployeeSkillId(1);
        dto.setEmployeeId(101);
        dto.setSkillId(201);
        dto.setSkillLevel("Advanced");
        dto.setExperience(3.5);

        assertEquals(1, dto.getEmployeeSkillId());
        assertEquals(101, dto.getEmployeeId());
        assertEquals(201, dto.getSkillId());
        assertEquals("Advanced", dto.getSkillLevel());
        assertEquals(3.5, dto.getExperience());
    }
}