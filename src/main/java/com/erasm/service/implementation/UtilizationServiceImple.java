package com.erasm.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erasm.dto.UtilizationDTO;
import com.erasm.entity.Allocation;
import com.erasm.entity.Employee;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.repository.AllocationRepository;
import com.erasm.repository.EmployeeRepository;
import com.erasm.service.UtilizationService;
@Service
public class UtilizationServiceImple implements UtilizationService {
	
	private final EmployeeRepository employeeRepository ;
	private final AllocationRepository allocationRepository;
	
	
	public UtilizationServiceImple(EmployeeRepository employeeRepository, AllocationRepository allocationRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.allocationRepository = allocationRepository;
	}

	@Override
	public UtilizationDTO getEmployeeUtilization(int employeeId) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()->new EmployeeNotFoundException(
						"Employee not found with id:"+employeeId));
		
		List<Allocation> allocations=allocationRepository.findByEmployeeEmployeeId(employeeId);
		int billable=0;
		for(Allocation allocation:allocations) {
			billable+=allocation.getAllocationPercentage();
		}
		int bench=100-billable;
		UtilizationDTO dto=new UtilizationDTO();
		dto.setEmployeeId(employee.getEmployeeId());
		dto.setEmployeeName(employee.getEmployeeName());
		dto.setBillablePercentage(billable);
		dto.setBenchPercentage(bench);
		
		
		return dto;
		
	}

	
	@Override
	public List<UtilizationDTO> getAllEmployeesUtilization(){
		List<Employee> employees=employeeRepository.findAll();
		List<UtilizationDTO> utilizationList=new ArrayList<>();
		for(Employee employee:employees) {
			utilizationList.add(getEmployeeUtilization(employee.getEmployeeId()));
		}
		return utilizationList;
		
	}

			
}
		

