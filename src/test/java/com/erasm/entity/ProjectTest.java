package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ProjectTest {

    @Test
    void testNoArgsConstructor() {
        Project project = new Project();
        assertNotNull(project);
    }

    @Test
    void testSettersAndGetters() {

        Project project = new Project();

        project.setProjectId(1);
        project.setProjectName("ERASM");
        project.setClientName("Infosys");
        project.setStartDate(LocalDate.of(2025, 1, 1));
        project.setEndDate(LocalDate.of(2025, 12, 31));
        project.setTechnologyStack("Spring Boot");
        project.setBudget(500000);

        project.setResourceRequests(new ArrayList<>());
        project.setAllocations(new ArrayList<>());

        assertEquals(1, project.getProjectId());
        assertEquals("ERASM", project.getProjectName());
        assertEquals("Infosys", project.getClientName());
        assertEquals(LocalDate.of(2025, 1, 1), project.getStartDate());
        assertEquals(LocalDate.of(2025, 12, 31), project.getEndDate());
        assertEquals("Spring Boot", project.getTechnologyStack());
        assertEquals(500000, project.getBudget());

        assertNotNull(project.getResourceRequests());
        assertNotNull(project.getAllocations());
    }

    @Test
    void testAllArgsConstructor() {

        Project project = new Project(
                1,
                "ERASM",
                "Infosys",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31),
                "Spring Boot",
                500000,
                new ArrayList<>(),
                new ArrayList<>());

        assertEquals(1, project.getProjectId());
        assertEquals("ERASM", project.getProjectName());
        assertEquals("Infosys", project.getClientName());
        assertEquals(LocalDate.of(2025, 1, 1), project.getStartDate());
        assertEquals(LocalDate.of(2025, 12, 31), project.getEndDate());
        assertEquals("Spring Boot", project.getTechnologyStack());
        assertEquals(500000, project.getBudget());
        assertNotNull(project.getResourceRequests());
        assertNotNull(project.getAllocations());
    }
}