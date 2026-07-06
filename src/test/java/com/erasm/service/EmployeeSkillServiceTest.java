package com.erasm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.erasm.service.implementation.EmployeeSkillServiceImple;

@ExtendWith(MockitoExtension.class)
public class EmployeeSkillServiceTest {

    @Mock
    private EmployeeSkillRepository employeeSkillRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private EmployeeSkillServiceImple employeeSkillService;

    @Test
    void testAddEmployeeSkill() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Skill skill = new Skill();
        skill.setSkillId(1);

        EmployeeSkill employeeSkill = new EmployeeSkill();

        EmployeeSkillDTO dto = new EmployeeSkillDTO();
        dto.setEmployeeId(1);
        dto.setSkillId(1);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(skillRepository.findById(1))
                .thenReturn(Optional.of(skill));

        when(employeeSkillRepository.save(any(EmployeeSkill.class)))
                .thenReturn(employeeSkill);

        EmployeeSkillDTO result = employeeSkillService.addEmployeeSkill(dto);

        verify(employeeSkillRepository).save(any(EmployeeSkill.class));
    }

    @Test
    void testAddEmployeeSkillEmployeeNotFound() {

        EmployeeSkillDTO dto = new EmployeeSkillDTO();
        dto.setEmployeeId(1);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeSkillService.addEmployeeSkill(dto));
    }

    @Test
    void testAddEmployeeSkillSkillNotFound() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        EmployeeSkillDTO dto = new EmployeeSkillDTO();
        dto.setEmployeeId(1);
        dto.setSkillId(1);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(skillRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(SkillNotFound.class,
                () -> employeeSkillService.addEmployeeSkill(dto));
    }

    @Test
    void testGetEmployeeSkillById() {

        EmployeeSkill employeeSkill = new EmployeeSkill();

        when(employeeSkillRepository.findById(1))
                .thenReturn(Optional.of(employeeSkill));

        employeeSkillService.getEmployeeSkillById(1);

        verify(employeeSkillRepository).findById(1);
    }

    @Test
    void testGetAllEmployeeSkills() {

        EmployeeSkill e1 = new EmployeeSkill();
        EmployeeSkill e2 = new EmployeeSkill();

        when(employeeSkillRepository.findAll())
                .thenReturn(Arrays.asList(e1, e2));

        List<EmployeeSkillDTO> list =
                employeeSkillService.getAllEmployeeSkills();

        assertEquals(2, list.size());

        verify(employeeSkillRepository).findAll();
    }

    @Test
    void testUpdateEmployeeSkill() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Skill skill = new Skill();
        skill.setSkillId(1);

        EmployeeSkill employeeSkill = new EmployeeSkill();

        EmployeeSkillDTO dto = new EmployeeSkillDTO();
        dto.setEmployeeId(1);
        dto.setSkillId(1);
        dto.setSkillLevel("Advanced");
        dto.setExperience(5.0);

        when(employeeSkillRepository.findById(1))
                .thenReturn(Optional.of(employeeSkill));

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(skillRepository.findById(1))
                .thenReturn(Optional.of(skill));

        when(employeeSkillRepository.save(any(EmployeeSkill.class)))
                .thenReturn(employeeSkill);

        employeeSkillService.updateEmployeeSkill(1, dto);

        verify(employeeSkillRepository).save(any(EmployeeSkill.class));
    }

    @Test
    void testDeleteEmployeeSkill() {

        when(employeeSkillRepository.existsById(1))
                .thenReturn(true);

        employeeSkillService.deleteEmployeeSkill(1);

        verify(employeeSkillRepository).deleteById(1);
    }

    @Test
    void testDeleteEmployeeSkillNotFound() {

        when(employeeSkillRepository.existsById(1))
                .thenReturn(false);

        assertThrows(EmployeeSkillNotFoundException.class,
                () -> employeeSkillService.deleteEmployeeSkill(1));
    }

}