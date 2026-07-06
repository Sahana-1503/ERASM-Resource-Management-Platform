package com.erasm.service;

import java.util.List;

import com.erasm.dto.EmployeeSkillDTO;

public interface EmployeeSkillService {
	EmployeeSkillDTO addEmployeeSkill(EmployeeSkillDTO employeSkillDTO);
	EmployeeSkillDTO updateEmployeeSkill(int employeeSkillId,EmployeeSkillDTO employeeSkillDTO);
	void deleteEmployeeSkill(int employeeSkillId);
	EmployeeSkillDTO getEmployeeSkillById(int employeeSkillId);
	List<EmployeeSkillDTO> getAllEmployeeSkills();


}
