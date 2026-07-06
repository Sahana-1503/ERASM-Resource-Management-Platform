package com.erasm.mapper;

import com.erasm.dto.EmployeeSkillDTO;
import com.erasm.entity.EmployeeSkill;

public class EmployeeSkillMapper {
	public static EmployeeSkillDTO toDTO(EmployeeSkill employeeSkill) {
		if(employeeSkill==null) {
			return null;
		}
		EmployeeSkillDTO dto=new EmployeeSkillDTO();
		dto.setEmployeeSkillId(employeeSkill.getEmployeeskillId());
		
		if(employeeSkill.getEmployee()!=null) {
			dto.setEmployeeId(employeeSkill.getEmployee().getEmployeeId());
			
		}
		
		if(employeeSkill.getSkill()!=null) {
			dto.setSkillId(employeeSkill.getSkill().getSkillId());
		}
		
		dto.setSkillLevel(employeeSkill.getSkillLevel());
		dto.setExperience(employeeSkill.getExperience());
		return dto;
	}
	
	public static EmployeeSkill toEntity(EmployeeSkillDTO dto) {
		if(dto==null) {
			return null;
		}
		EmployeeSkill employeeSkill=new EmployeeSkill();
		employeeSkill.setEmployeeskillId(dto.getEmployeeSkillId());
		
		

		
		employeeSkill.setSkillLevel(dto.getSkillLevel());
		employeeSkill.setExperience(dto.getExperience());
		return employeeSkill;
	}

	

}
