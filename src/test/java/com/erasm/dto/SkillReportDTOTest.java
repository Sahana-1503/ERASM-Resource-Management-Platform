package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SkillReportDTOTest {

    @Test
    void testNoArgsConstructor() {
        SkillReportDTO dto = new SkillReportDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {

        List<String> employees = Arrays.asList("Sahana", "Rahul");

        SkillReportDTO dto = new SkillReportDTO("Java", employees);

        assertEquals("Java", dto.getSkillName());
        assertEquals(employees, dto.getEmployeeNames());
    }

    @Test
    void testSettersAndGetters() {

        SkillReportDTO dto = new SkillReportDTO();

        List<String> employees = Arrays.asList("A", "B", "C");

        dto.setSkillName("Spring Boot");
        dto.setEmployeeNames(employees);

        assertEquals("Spring Boot", dto.getSkillName());
        assertEquals(employees, dto.getEmployeeNames());
    }
}