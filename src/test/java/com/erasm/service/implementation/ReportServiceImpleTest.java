package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
import com.erasm.service.UtilizationService;

@ExtendWith(MockitoExtension.class)
class ReportServiceImpleTest {

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

    private Skill skill;
    private Employee employee;
    private EmployeeSkill employeeSkill;
    private Allocation allocation;

    @BeforeEach
    void setUp() {

        skill = new Skill();
        skill.setSkillId(1);
        skill.setSkillName("Java");

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sahana");

        employeeSkill = new EmployeeSkill();
        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);

        allocation = new Allocation();
        allocation.setAllocationId(1);
        allocation.setEmployee(employee);
    }

    @Test
    void testGetSkillReport() {

        when(skillRepository.findAll())
                .thenReturn(Arrays.asList(skill));

        when(employeeSkillRepository.findAll())
                .thenReturn(Arrays.asList(employeeSkill));

        List<SkillReportDTO> report = reportService.getSkillReport();

        assertEquals(1, report.size());
        assertEquals("Java", report.get(0).getSkillName());
        assertEquals("Sahana",
                report.get(0).getEmployeeNames().get(0));
    }

    @Test
    void testGetSkillReportEmpty() {

        when(skillRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<SkillReportDTO> report = reportService.getSkillReport();

        assertTrue(report.isEmpty());
    }

    @Test
    void testGetUtilizationReport() {

        UtilizationDTO dto = new UtilizationDTO();

        when(utilizationService.getAllEmployeesUtilization())
                .thenReturn(Arrays.asList(dto));

        List<UtilizationDTO> result =
                reportService.getUtilizationReport();

        assertEquals(1, result.size());
    }

    @Test
    void testGetProjectAllocationReport() {

        when(allocationRepository.findAll())
                .thenReturn(Arrays.asList(allocation));

        List<AllocationDTO> result =
                reportService.getProjectAllocationReport();

        assertEquals(1, result.size());
    }
}