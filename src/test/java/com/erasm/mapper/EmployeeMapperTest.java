package com.erasm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.erasm.dto.EmployeeDTO;
import com.erasm.entity.Employee;
import com.erasm.entity.User;

public class EmployeeMapperTest {

    @Test
    void testToDTO() {

        User user = new User();
        user.setUserid(10);

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sahana");
        employee.setPhoneNumber("9999999999");
        employee.setDepartment("IT");
        employee.setDesignation("Developer");
        employee.setExperience(2.5);
        employee.setBenchStatus("Available");
        employee.setUser(user);

        EmployeeDTO dto = EmployeeMapper.toDTO(employee);

        assertEquals(1, dto.getEmployeeId());
        assertEquals(10, dto.getUserId());
    }

    @Test
    void testToEntity() {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("Sahana");
        dto.setPhoneNumber("999");
        dto.setDepartment("IT");
        dto.setDesignation("Developer");
        dto.setExperience(2.5);
        dto.setBenchStatus("Available");

        Employee employee = EmployeeMapper.toEntity(dto);

        assertEquals(1, employee.getEmployeeId());
        assertEquals("Sahana", employee.getEmployeeName());
    }

    @Test
    void testNull() {
        assertNull(EmployeeMapper.toDTO(null));
        assertNull(EmployeeMapper.toEntity(null));
    }
}