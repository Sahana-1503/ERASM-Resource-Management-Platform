package com.erasm.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erasm.dto.AllocationDTO;
import com.erasm.entity.Allocation;
import com.erasm.entity.Employee;
import com.erasm.entity.Project;
import com.erasm.exception.AllocationException;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.exception.ProjectNotFound;
import com.erasm.mapper.AllocationMapper;
import com.erasm.repository.AllocationRepository;
import com.erasm.repository.EmployeeRepository;
import com.erasm.repository.ProjectRepository;
import com.erasm.service.AllocationService;

@Service
public class AllocationServiceImple implements AllocationService {

    private final AllocationRepository allocationRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    private static final Logger logger =
            LoggerFactory.getLogger(AllocationServiceImple.class);

    public AllocationServiceImple(AllocationRepository allocationRepository,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {

        this.allocationRepository = allocationRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public AllocationDTO addAllocation(AllocationDTO allocationDTO) {

        try {

            Employee employee = employeeRepository.findById(allocationDTO.getEmployeeId())
                    .orElseThrow(() -> new EmployeeNotFoundException(
                            "Employee not found with id: " + allocationDTO.getEmployeeId()));

            Project project = projectRepository.findById(allocationDTO.getProjectId())
                    .orElseThrow(() -> new ProjectNotFound(
                            "Project not found with id: " + allocationDTO.getProjectId()));

            List<Allocation> allocations =
                    allocationRepository.findByEmployeeEmployeeId(employee.getEmployeeId());

            int total = 0;

            for (Allocation allocation : allocations) {
                total += allocation.getAllocationPercentage();
            }

            total += allocationDTO.getAllocationPercentage();

            if (total > 100) {

                logger.warn(
                        "Invalid allocation request. Allocation exceeds 100% for Employee {}",
                        employee.getEmployeeId());

                throw new AllocationException(
                        "Employee Allocation cannot exceed 100%");
            }

            Allocation allocation = AllocationMapper.toEntity(allocationDTO);

            allocation.setEmployee(employee);
            allocation.setProject(project);

            Allocation savedAllocation = allocationRepository.save(allocation);

            
            logger.info(
                    "Employee {} allocated to Project {} successfully.",
                    employee.getEmployeeId(),
                    project.getProjectId());

            return AllocationMapper.toDTO(savedAllocation);

        } catch (Exception e) {

            logger.error("Database failure while creating resource allocation.", e);

            throw e;
        }
    }

    @Override
    public AllocationDTO updateAllocation(int allocationId,
            AllocationDTO allocationDTO) {

        try {

            Allocation allocation = allocationRepository.findById(allocationId)
                    .orElseThrow(() -> new AllocationException(
                            "Allocation not found with id: " + allocationId));

            Employee employee = employeeRepository.findById(allocationDTO.getEmployeeId())
                    .orElseThrow(() -> new EmployeeNotFoundException(
                            "Employee not found with id: " + allocationDTO.getEmployeeId()));

            Project project = projectRepository.findById(allocationDTO.getProjectId())
                    .orElseThrow(() -> new ProjectNotFound(
                            "Project not found with id: " + allocationDTO.getProjectId()));

            List<Allocation> allocations =
                    allocationRepository.findByEmployeeEmployeeId(employee.getEmployeeId());

            int total = 0;

            for (Allocation a : allocations) {

                if (a.getAllocationId() != allocationId) {
                    total += a.getAllocationPercentage();
                }
            }

            total += allocationDTO.getAllocationPercentage();

            if (total > 100) {

                logger.warn(
                        "Invalid allocation request. Allocation exceeds 100% for Employee {}",
                        employee.getEmployeeId());

                throw new AllocationException(
                        "Employee Allocation cannot exceed 100%");
            }

            allocation.setEmployee(employee);
            allocation.setProject(project);
            allocation.setAllocationPercentage(allocationDTO.getAllocationPercentage());
            allocation.setAllocationDate(allocationDTO.getAllocationDate());
            allocation.setReleaseDate(allocationDTO.getReleaseDate());
            allocation.setStatus(allocationDTO.getStatus());

            Allocation updatedAllocation = allocationRepository.save(allocation);

            logger.info(
                    "Resource allocation updated successfully for Employee {} and Project {}.",
                    employee.getEmployeeId(),
                    project.getProjectId());

            return AllocationMapper.toDTO(updatedAllocation);

        } catch (Exception e) {

            logger.error("Database failure while updating resource allocation.", e);

            throw e;
        }
    }

    @Override
    public void deleteAllocation(int allocationId) {

        try {

            if (!allocationRepository.existsById(allocationId)) {

                logger.warn("Invalid request. Allocation not found with ID {}",
                        allocationId);

                throw new AllocationException(
                        "Allocation not found with id: " + allocationId);
            }

            allocationRepository.deleteById(allocationId);

            logger.info("Allocation deleted successfully with ID {}",
                    allocationId);

        } catch (Exception e) {

            logger.error("Database failure while deleting allocation.", e);

            throw e;
        }
    }

    @Override
    public AllocationDTO getAllocationById(int allocationId) {

        Allocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() ->
                        new AllocationException(
                                "Allocation not found with id: " + allocationId));

        return AllocationMapper.toDTO(allocation);
    }

    @Override
    public List<AllocationDTO> getAllAllocations() {

        return allocationRepository.findAll()
                .stream()
                .map(AllocationMapper::toDTO)
                .toList();
    }
}