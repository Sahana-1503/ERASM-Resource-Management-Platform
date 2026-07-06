package com.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.EmployeeSkillDTO;
import com.erasm.service.EmployeeSkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee-skills")
public class EmployeeSkillController {
	private final EmployeeSkillService employeeSkillService;

	public EmployeeSkillController(EmployeeSkillService employeeSkillService) {
		super();
		this.employeeSkillService = employeeSkillService;
	}
	@PostMapping
	public ResponseEntity<EmployeeSkillDTO> addEmployeeSkill(@Valid @RequestBody EmployeeSkillDTO employeeSkillDTO){
		EmployeeSkillDTO savedEmployeeSkill=employeeSkillService.addEmployeeSkill(employeeSkillDTO);
		return new ResponseEntity<>(savedEmployeeSkill,HttpStatus.CREATED);
		
		}
	
	@GetMapping
	public ResponseEntity<List<EmployeeSkillDTO>> getAllEmployeeSkill(){
		return ResponseEntity.ok(employeeSkillService.getAllEmployeeSkills());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeSkillDTO> getEmployeeSkillById(@PathVariable int id){
		return ResponseEntity.ok(employeeSkillService.getEmployeeSkillById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeSkillDTO> updateEmployeeSkill(
			@PathVariable int id,
			@Valid @RequestBody EmployeeSkillDTO employeeSkillDTO){
		return ResponseEntity.ok(employeeSkillService.updateEmployeeSkill(id, employeeSkillDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeSkill(@PathVariable int id){
		employeeSkillService.deleteEmployeeSkill(id);
		return ResponseEntity.ok("Employee Skill deleted Successfully");
	}
	
	

}
