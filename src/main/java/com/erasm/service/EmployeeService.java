package com.erasm.service;

import java.util.List;

import com.erasm.dto.EmployeeDTO;

public interface EmployeeService {
	EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
	EmployeeDTO updateEmployee(int employeeId,EmployeeDTO employeeDTO);
	void deleteEmployee(int employeeId);
	EmployeeDTO getEmployeeById(int employeeId);
	List<EmployeeDTO> getAllEmployees();

}
