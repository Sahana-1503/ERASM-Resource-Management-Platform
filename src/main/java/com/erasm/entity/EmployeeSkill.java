package com.erasm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_skills")
public class EmployeeSkill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int employeeskillId;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="skill_id")
	private Skill skill;
	
	private String skillLevel;
	
	private Double experience;
	
	public EmployeeSkill() {
		
	}

	public int getEmployeeskillId() {
		return employeeskillId;
	}

	public void setEmployeeskillId(int employeeskillId) {
		this.employeeskillId = employeeskillId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
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
