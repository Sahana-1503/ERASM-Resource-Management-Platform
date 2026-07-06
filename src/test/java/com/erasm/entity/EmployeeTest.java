package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

    @Test
    void testNoArgsConstructor() {
        Employee employee = new Employee();
        assertNotNull(employee);
    }

    @Test
    void testSettersAndGetters() {

        Employee employee = new Employee();

        User user = new User();

        employee.setEmployeeId(1);
        employee.setEmployeeName("Sahana");
        employee.setPhoneNumber("9876543210");
        employee.setDepartment("IT");
        employee.setDesignation("Developer");
        employee.setExperience(2.5);
        employee.setBenchStatus("Available");
        employee.setUser(user);

        employee.setEmployeeSkills(new ArrayList<>());
        employee.setCertificationss(new ArrayList<>());
        employee.setAllocations(new ArrayList<>());

        assertEquals(1, employee.getEmployeeId());
        assertEquals("Sahana", employee.getEmployeeName());
        assertEquals("9876543210", employee.getPhoneNumber());
        assertEquals("IT", employee.getDepartment());
        assertEquals("Developer", employee.getDesignation());
        assertEquals(2.5, employee.getExperience());
        assertEquals("Available", employee.getBenchStatus());
        assertEquals(user, employee.getUser());

        assertNotNull(employee.getEmployeeSkills());
        assertNotNull(employee.getCertificationss());
        assertNotNull(employee.getAllocations());
    }
}