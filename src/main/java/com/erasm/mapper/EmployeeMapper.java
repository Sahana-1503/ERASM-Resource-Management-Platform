package com.erasm.mapper;

import com.erasm.dto.EmployeeDTO;
import com.erasm.entity.Employee;

public class EmployeeMapper {
	public static EmployeeDTO toDTO(Employee employee) {
		if (employee==null) {
			return null;
			
		}
		EmployeeDTO dto=new EmployeeDTO();
		dto.setEmployeeId(employee.getEmployeeId());
		dto.setEmployeeName(employee.getEmployeeName());
		dto.setPhoneNumber(employee.getPhoneNumber());
		dto.setDepartment(employee.getDepartment());
		dto.setDesignation(employee.getDesignation());
		dto.setExperience(employee.getExperience());
		dto.setBenchStatus(employee.getBenchStatus());
		
		if(employee.getUser()!=null) {
			dto.setUserId(employee.getUser().getUserid());
		}

		
		return dto;
		
	}
	public static Employee toEntity(EmployeeDTO dto) {
		if(dto==null) {
			return null;
			
		}
		Employee employee=new Employee();
		employee.setEmployeeId(dto.getEmployeeId());
		employee.setEmployeeName(dto.getEmployeeName());
		employee.setPhoneNumber(dto.getPhoneNumber());
		employee.setDepartment(dto.getDepartment());
		employee.setDesignation(dto.getDesignation());
		employee.setExperience(dto.getExperience());
		employee.setBenchStatus(dto.getBenchStatus());
		
		return employee;
	}

}
