package com.erasm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.erasm.service.implementation.AllocationServiceImple;

@ExtendWith(MockitoExtension.class)
public class AllocationServiceTest {

    @Mock
    private AllocationRepository allocationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private AllocationServiceImple allocationService;

    @Test
    void testAddAllocation() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Project project = new Project();
        project.setProjectId(1);

        AllocationDTO dto = new AllocationDTO();
        dto.setEmployeeId(1);
        dto.setProjectId(1);
        dto.setAllocationPercentage(50);
        dto.setAllocationDate(LocalDate.now());
        dto.setReleaseDate(LocalDate.now().plusMonths(3));
        dto.setStatus("ACTIVE");

        Allocation allocation = new Allocation();

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(allocationRepository.findByEmployeeEmployeeId(1))
                .thenReturn(Collections.emptyList());
        when(allocationRepository.save(any(Allocation.class)))
                .thenReturn(allocation);

        allocationService.addAllocation(dto);

        verify(allocationRepository).save(any(Allocation.class));
    }

    @Test
    void testAddAllocationEmployeeNotFound() {

        AllocationDTO dto = new AllocationDTO();
        dto.setEmployeeId(1);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> allocationService.addAllocation(dto));
    }

    @Test
    void testAddAllocationProjectNotFound() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        AllocationDTO dto = new AllocationDTO();
        dto.setEmployeeId(1);
        dto.setProjectId(1);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFound.class,
                () -> allocationService.addAllocation(dto));
    }

    @Test
    void testAllocationExceeds100Percent() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Project project = new Project();
        project.setProjectId(1);

        Allocation old = new Allocation();
        old.setAllocationPercentage(70);

        AllocationDTO dto = new AllocationDTO();
        dto.setEmployeeId(1);
        dto.setProjectId(1);
        dto.setAllocationPercentage(40);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(allocationRepository.findByEmployeeEmployeeId(1))
                .thenReturn(List.of(old));

        assertThrows(AllocationException.class,
                () -> allocationService.addAllocation(dto));
    }

    @Test
    void testUpdateAllocation() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Project project = new Project();
        project.setProjectId(1);

        Allocation allocation = new Allocation();
        allocation.setAllocationId(1);

        AllocationDTO dto = new AllocationDTO();
        dto.setEmployeeId(1);
        dto.setProjectId(1);
        dto.setAllocationPercentage(60);
        dto.setAllocationDate(LocalDate.now());
        dto.setReleaseDate(LocalDate.now().plusMonths(2));
        dto.setStatus("ACTIVE");

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

        allocationService.updateAllocation(1, dto);

        verify(allocationRepository).save(any(Allocation.class));
    }

    @Test
    void testGetAllocationById() {

        Allocation allocation = new Allocation();

        when(allocationRepository.findById(1))
                .thenReturn(Optional.of(allocation));

        allocationService.getAllocationById(1);

        verify(allocationRepository).findById(1);
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

        Allocation a1 = new Allocation();
        Allocation a2 = new Allocation();

        when(allocationRepository.findAll())
                .thenReturn(Arrays.asList(a1, a2));

        List<AllocationDTO> list =
                allocationService.getAllAllocations();

        assertEquals(2, list.size());

        verify(allocationRepository).findAll();
    }

    @Test
    void testDeleteAllocation() {

        when(allocationRepository.existsById(1))
                .thenReturn(true);

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