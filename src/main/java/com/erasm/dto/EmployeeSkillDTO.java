package com.erasm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeSkillDTO {
	private int EmployeeSkillId;
	
	@NotNull(message="Employee ID is required")
	private Integer employeeId;
	
	@NotNull(message="Skill ID is required")
	private Integer skillId;
	
	@NotBlank(message="Skill Level is required")
	private String skillLevel;
	@NotNull(message="Experience is required")
	private Double experience;
	
	public EmployeeSkillDTO() {
		
	}

	public int getEmployeeSkillId() {
		return EmployeeSkillId;
	}

	public void setEmployeeSkillId(int employeeSkillId) {
		EmployeeSkillId = employeeSkillId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public Double getExperience() {
		return experience;
	}

	public void setExperience(Double experience) {
		this.experience = experience;
	}

}