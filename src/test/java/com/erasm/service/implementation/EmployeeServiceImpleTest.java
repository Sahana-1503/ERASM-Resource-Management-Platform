package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.EmployeeDTO;
import com.erasm.entity.Employee;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImpleTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImple employeeService;

    private Employee employee;
    private EmployeeDTO dto;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sahana");
        employee.setPhoneNumber("9876543210");
        employee.setDepartment("IT");
        employee.setDesignation("Developer");
        employee.setExperience(2.0);
        employee.setBenchStatus("Available");

        dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("Sahana");
        dto.setPhoneNumber("9876543210");
        dto.setDepartment("IT");
        dto.setDesignation("Developer");
        dto.setExperience(2.0);
        dto.setBenchStatus("Available");
    }

    @Test
    void testAddEmployee() {

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result = employeeService.addEmployee(dto);

        assertNotNull(result);
        assertEquals("Sahana", result.getEmployeeName());
    }

    @Test
    void testGetEmployeeById() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        EmployeeDTO result = employeeService.getEmployeeById(1);

        assertEquals("Sahana", result.getEmployeeName());
    }

    @Test
    void testGetEmployeeById_NotFound() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(1));
    }

    @Test
    void testGetAllEmployees() {

        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee));

        assertEquals(1, employeeService.getAllEmployees().size());
    }

    @Test
    void testUpdateEmployee() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeDTO result = employeeService.updateEmployee(1, dto);

        assertEquals("Sahana", result.getEmployeeName());
    }

    @Test
    void testUpdateEmployee_NotFound() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.updateEmployee(1, dto));
    }

    @Test
    void testDeleteEmployee() {

        when(employeeRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(employeeRepository).deleteById(1);

        employeeService.deleteEmployee(1);

        verify(employeeRepository).deleteById(1);
    }

    @Test
    void testDeleteEmployee_NotFound() {

        when(employeeRepository.existsById(1))
                .thenReturn(false);

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.deleteEmployee(1));
    }
}