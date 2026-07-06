package com.erasm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.UtilizationDTO;
import com.erasm.service.UtilizationService;

@RestController
@RequestMapping("/utilization")
public class UtilizationController {
	private final UtilizationService utilizationService;

	public UtilizationController(UtilizationService utilizationService) {
		super();
		this.utilizationService = utilizationService;
	}
	@GetMapping("/{employeeId}")
	public ResponseEntity<UtilizationDTO> getEmployeeUtilization(@PathVariable int employeeId){
		return ResponseEntity.ok(utilizationService.getEmployeeUtilization(employeeId));
		
	}
	@GetMapping
	public ResponseEntity<List<UtilizationDTO>> getAllEmployeesUtilization(){
		return ResponseEntity.ok(utilizationService.getAllEmployeesUtilization());
		
	}

	

}
