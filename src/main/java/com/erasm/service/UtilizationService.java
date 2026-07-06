package com.erasm.service;

import java.util.List;

import com.erasm.dto.UtilizationDTO;

public interface UtilizationService {
	UtilizationDTO getEmployeeUtilization(int employeeId);
	List<UtilizationDTO> getAllEmployeesUtilization();

}
