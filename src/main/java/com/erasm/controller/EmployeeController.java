package com.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.EmployeeDTO;
import com.erasm.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	@PostMapping
	public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
		EmployeeDTO savedEmployee=employeeService.addEmployee(employeeDTO);
		return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
		
		}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id){
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(
			@PathVariable int id,
			@Valid @RequestBody EmployeeDTO employeeDTO){
		return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok("Employee deleted Successfully");
	}
	
	

}
