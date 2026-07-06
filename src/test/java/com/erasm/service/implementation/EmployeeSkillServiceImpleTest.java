package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.EmployeeSkillDTO;
import com.erasm.entity.Employee;
import com.erasm.entity.EmployeeSkill;
import com.erasm.entity.Skill;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.exception.EmployeeSkillNotFoundException;
import com.erasm.exception.SkillNotFound;
import com.erasm.repository.EmployeeRepository;
import com.erasm.repository.EmployeeSkillRepository;
import com.erasm.repository.SkillRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeSkillServiceImpleTest {

    @Mock
    private EmployeeSkillRepository employeeSkillRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private EmployeeSkillServiceImple service;

    private Employee employee;
    private Skill skill;
    private EmployeeSkill employeeSkill;
    private EmployeeSkillDTO dto;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sahana");

        skill = new Skill();
        skill.setSkillId(1);
        skill.setSkillName("Java");

        employeeSkill = new EmployeeSkill();
        employeeSkill.setEmployeeskillId(1);
        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);
        employeeSkill.setSkillLevel("Advanced");
        employeeSkill.setExperience(3.0);

        dto = new EmployeeSkillDTO();
        dto.setEmployeeSkillId(1);
        dto.setEmployeeId(1);
        dto.setSkillId(1);
        dto.setSkillLevel("Advanced");
        dto.setExperience(3.0);
    }

    @Test
    void testAddEmployeeSkill() {

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        when(employeeSkillRepository.save(any(EmployeeSkill.class))).thenReturn(employeeSkill);

        EmployeeSkillDTO result = service.addEmployeeSkill(dto);

        assertNotNull(result);
        assertEquals("Advanced", result.getSkillLevel());

        verify(employeeSkillRepository).save(any(EmployeeSkill.class));
    }

    @Test
    void testAddEmployeeSkillEmployeeNotFound() {

        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> service.addEmployeeSkill(dto));
    }

    @Test
    void testAddEmployeeSkillSkillNotFound() {

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(skillRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(SkillNotFound.class,
                () -> service.addEmployeeSkill(dto));
    }

    @Test
    void testGetEmployeeSkillById() {

        when(employeeSkillRepository.findById(1))
                .thenReturn(Optional.of(employeeSkill));

        EmployeeSkillDTO result = service.getEmployeeSkillById(1);

        assertEquals("Advanced", result.getSkillLevel());
    }

    @Test
    void testGetEmployeeSkillByIdNotFound() {

        when(employeeSkillRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.getEmployeeSkillById(1));
    }

    @Test
    void testGetAllEmployeeSkills() {

        when(employeeSkillRepository.findAll())
                .thenReturn(Arrays.asList(employeeSkill));

        assertEquals(1, service.getAllEmployeeSkills().size());
    }

    @Test
    void testUpdateEmployeeSkill() {

        when(employeeSkillRepository.findById(1))
                .thenReturn(Optional.of(employeeSkill));

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(skillRepository.findById(1))
                .thenReturn(Optional.of(skill));

        when(employeeSkillRepository.save(any(EmployeeSkill.class)))
                .thenReturn(employeeSkill);

        EmployeeSkillDTO result = service.updateEmployeeSkill(1, dto);

        assertNotNull(result);

        verify(employeeSkillRepository).save(any(EmployeeSkill.class));
    }

    @Test
    void testDeleteEmployeeSkill() {

        when(employeeSkillRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(employeeSkillRepository).deleteById(1);

        service.deleteEmployeeSkill(1);

        verify(employeeSkillRepository).deleteById(1);
    }

    @Test
    void testDeleteEmployeeSkillNotFound() {

        when(employeeSkillRepository.existsById(1))
                .thenReturn(false);

        assertThrows(EmployeeSkillNotFoundException.class,
                () -> service.deleteEmployeeSkill(1));
    }
}