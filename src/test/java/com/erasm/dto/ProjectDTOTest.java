package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ProjectDTOTest {

    @Test
    void testNoArgsConstructor() {
        ProjectDTO dto = new ProjectDTO();
        assertNotNull(dto);
    }

    @Test
    void testSettersAndGetters() {

        ProjectDTO dto = new ProjectDTO();

        dto.setProjectId(1);
        dto.setProjectName("ERASM");
        dto.setClientName("Infosys");
        dto.setStartDate(LocalDate.of(2025, 1, 1));
        dto.setEndDate(LocalDate.of(2025, 12, 31));
        dto.setTechnologyStack("Spring Boot");
        dto.setBudget(500000);

        assertEquals(1, dto.getProjectId());
        assertEquals("ERASM", dto.getProjectName());
        assertEquals("Infosys", dto.getClientName());
        assertEquals(LocalDate.of(2025, 1, 1), dto.getStartDate());
        assertEquals(LocalDate.of(2025, 12, 31), dto.getEndDate());
        assertEquals("Spring Boot", dto.getTechnologyStack());
        assertEquals(500000, dto.getBudget());
    }
}