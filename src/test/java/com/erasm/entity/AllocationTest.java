package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AllocationTest {

    @Test
    void testNoArgsConstructor() {
        Allocation allocation = new Allocation();
        assertNotNull(allocation);
    }

    @Test
    void testAllArgsConstructor() {

        Employee employee = new Employee();
        Project project = new Project();

        Allocation allocation = new Allocation(
                1,
                80,
                employee,
                project,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31),
                "ALLOCATED");

        assertEquals(1, allocation.getAllocationId());
        assertEquals(80, allocation.getAllocationPercentage());
        assertEquals(employee, allocation.getEmployee());
        assertEquals(project, allocation.getProject());
        assertEquals(LocalDate.of(2025, 1, 1), allocation.getAllocationDate());
        assertEquals(LocalDate.of(2025, 12, 31), allocation.getReleaseDate());
        assertEquals("ALLOCATED", allocation.getStatus());
    }

    @Test
    void testSettersAndGetters() {

        Allocation allocation = new Allocation();

        Employee employee = new Employee();
        Project project = new Project();

        allocation.setAllocationId(2);
        allocation.setAllocationPercentage(60);
        allocation.setEmployee(employee);
        allocation.setProject(project);
        allocation.setAllocationDate(LocalDate.of(2025, 2, 1));
        allocation.setReleaseDate(LocalDate.of(2025, 11, 30));
        allocation.setStatus("ACTIVE");

        assertEquals(2, allocation.getAllocationId());
        assertEquals(60, allocation.getAllocationPercentage());
        assertEquals(employee, allocation.getEmployee());
        assertEquals(project, allocation.getProject());
        assertEquals(LocalDate.of(2025, 2, 1), allocation.getAllocationDate());
        assertEquals(LocalDate.of(2025, 11, 30), allocation.getReleaseDate());
        assertEquals("ACTIVE", allocation.getStatus());
    }
}