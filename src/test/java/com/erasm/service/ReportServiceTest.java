package com.erasm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.AllocationDTO;
import com.erasm.dto.SkillReportDTO;
import com.erasm.dto.UtilizationDTO;
import com.erasm.entity.Allocation;
import com.erasm.entity.Employee;
import com.erasm.entity.EmployeeSkill;
import com.erasm.entity.Skill;
import com.erasm.repository.AllocationRepository;
import com.erasm.repository.EmployeeSkillRepository;
import com.erasm.repository.SkillRepository;
import com.erasm.service.implementation.ReportServiceImple;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private EmployeeSkillRepository employeeSkillRepository;

    @Mock
    private AllocationRepository allocationRepository;

    @Mock
    private UtilizationService utilizationService;

    @InjectMocks
    private ReportServiceImple reportService;

    @Test
    void testGetSkillReport() {

        Skill skill = new Skill();
        skill.setSkillId(1);
        skill.setSkillName("Java");

        Employee employee = new Employee();
        employee.setEmployeeName("Sahana");

        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setSkill(skill);
        employeeSkill.setEmployee(employee);

        when(skillRepository.findAll())
                .thenReturn(Arrays.asList(skill));

        when(employeeSkillRepository.findAll())
                .thenReturn(Arrays.asList(employeeSkill));

        List<SkillReportDTO> report = reportService.getSkillReport();

        assertEquals(1, report.size());
        assertEquals("Java", report.get(0).getSkillName());
        assertEquals("Sahana", report.get(0).getEmployeeNames().get(0));

        verify(skillRepository).findAll();
        verify(employeeSkillRepository).findAll();
    }

    @Test
    void testGetUtilizationReport() {

        UtilizationDTO dto = new UtilizationDTO();

        when(utilizationService.getAllEmployeesUtilization())
                .thenReturn(Arrays.asList(dto));

        List<UtilizationDTO> list = reportService.getUtilizationReport();

        assertEquals(1, list.size());

        verify(utilizationService).getAllEmployeesUtilization();
    }

    @Test
    void testGetProjectAllocationReport() {

        Allocation allocation = new Allocation();

        when(allocationRepository.findAll())
                .thenReturn(Arrays.asList(allocation));

        List<AllocationDTO> list = reportService.getProjectAllocationReport();

        assertEquals(1, list.size());

        verify(allocationRepository).findAll();
    }
}