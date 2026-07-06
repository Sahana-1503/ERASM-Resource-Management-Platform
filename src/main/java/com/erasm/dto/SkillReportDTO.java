package com.erasm.dto;

import java.util.List;

public class SkillReportDTO {
	private String skillName;
	private List<String> employeeNames;


	public SkillReportDTO() {
	
}


	public SkillReportDTO(String skillName, List<String> employeeNames) {
		super();
		this.skillName = skillName;
		this.employeeNames = employeeNames;
	}


	public String getSkillName() {
		return skillName;
	}


	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}


	public List<String> getEmployeeNames() {
		return employeeNames;
	}


	public void setEmployeeNames(List<String> employeeNames) {
		this.employeeNames = employeeNames;
	}
}
