package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.AllocationDTO;
import com.erasm.entity.Allocation;
import com.erasm.entity.Employee;
import com.erasm.entity.Project;
import com.erasm.exception.AllocationException;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.exception.ProjectNotFound;
import com.erasm.repository.AllocationRepository;
import com.erasm.repository.EmployeeRepository;
import com.erasm.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class AllocationServiceImpleTest {

    @Mock
    private AllocationRepository allocationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private AllocationServiceImple allocationService;

    private Employee employee;
    private Project project;
    private Allocation allocation;
    private AllocationDTO dto;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sahana");

        project = new Project();
        project.setProjectId(1);
        project.setProjectName("ERASM");

        allocation = new Allocation();
        allocation.setAllocationId(1);
        allocation.setEmployee(employee);
        allocation.setProject(project);
        allocation.setAllocationPercentage(40);
        allocation.setAllocationDate(LocalDate.now());
        allocation.setReleaseDate(LocalDate.now().plusDays(30));
        allocation.setStatus("ACTIVE");

        dto = new AllocationDTO();
        dto.setAllocationId(1);
        dto.setEmployeeId(1);
        dto.setProjectId(1);
        dto.setAllocationPercentage(40);
        dto.setAllocationDate(LocalDate.now());
        dto.setReleaseDate(LocalDate.now().plusDays(30));
        dto.setStatus("ACTIVE");
    }

    @Test
    void testAddAllocation() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(allocationRepository.findByEmployeeEmployeeId(1))
                .thenReturn(Collections.emptyList());

        when(allocationRepository.save(any(Allocation.class)))
                .thenReturn(allocation);

        AllocationDTO result = allocationService.addAllocation(dto);

        assertNotNull(result);
        assertEquals(40, result.getAllocationPercentage());

        verify(allocationRepository).save(any(Allocation.class));
    }

    @Test
    void testAddAllocationEmployeeNotFound() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> allocationService.addAllocation(dto));
    }

    @Test
    void testAddAllocationProjectNotFound() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFound.class,
                () -> allocationService.addAllocation(dto));
    }

    @Test
    void testAddAllocationMoreThan100Percent() {

        Allocation old = new Allocation();
        old.setAllocationPercentage(80);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(allocationRepository.findByEmployeeEmployeeId(1))
                .thenReturn(Arrays.asList(old));

        assertThrows(AllocationException.class,
                () -> allocationService.addAllocation(dto));
    }

    @Test
    void testGetAllocationById() {

        when(allocationRepository.findById(1))
                .thenReturn(Optional.of(allocation));

        AllocationDTO result = allocationService.getAllocationById(1);

        assertEquals(1, result.getAllocationId());
    }

    @Test
    void testGetAllocationByIdNotFound() {

        when(allocationRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(AllocationException.class,
                () -> allocationService.getAllocationById(1));
    }

    @Test
    void testGetAllAllocations() {

        when(allocationRepository.findAll())
                .thenReturn(Arrays.asList(allocation));

        assertEquals(1, allocationService.getAllAllocations().size());
    }

    @Test
    void testUpdateAllocation() {

        when(allocationRepository.findById(1))
                .thenReturn(Optional.of(allocation));

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(allocationRepository.findByEmployeeEmployeeId(1))
                .thenReturn(Collections.emptyList());

        when(allocationRepository.save(any(Allocation.class)))
                .thenReturn(allocation);

        AllocationDTO result = allocationService.updateAllocation(1, dto);

        assertNotNull(result);

        verify(allocationRepository).save(any(Allocation.class));
    }

    @Test
    void testDeleteAllocation() {

        when(allocationRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(allocationRepository).deleteById(1);

        allocationService.deleteAllocation(1);

        verify(allocationRepository).deleteById(1);
    }

    @Test
    void testDeleteAllocationNotFound() {

        when(allocationRepository.existsById(1))
                .thenReturn(false);

        assertThrows(AllocationException.class,
                () -> allocationService.deleteAllocation(1));
    }

}