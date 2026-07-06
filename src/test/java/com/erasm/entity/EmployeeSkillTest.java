package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployeeSkillTest {

    @Test
    void testNoArgsConstructor() {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        assertNotNull(employeeSkill);
    }

    @Test
    void testSettersAndGetters() {

        EmployeeSkill employeeSkill = new EmployeeSkill();

        Employee employee = new Employee();
        Skill skill = new Skill();

        employeeSkill.setEmployeeskillId(1);
        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);
        employeeSkill.setSkillLevel("Advanced");
        employeeSkill.setExperience(3.5);

        assertEquals(1, employeeSkill.getEmployeeskillId());
        assertEquals(employee, employeeSkill.getEmployee());
        assertEquals(skill, employeeSkill.getSkill());
        assertEquals("Advanced", employeeSkill.getSkillLevel());
        assertEquals(3.5, employeeSkill.getExperience());
    }
}