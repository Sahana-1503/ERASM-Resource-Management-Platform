package com.erasm.entity;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
@Entity
@Table(name="skills")
public class Skill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="skill_id")
	private int skillId;
	
	@Column(name="skill_name",nullable=false,unique=true)
	private String skillName;
	
	@OneToMany(mappedBy="skill",
			cascade=CascadeType.ALL,
			fetch=FetchType.LAZY)
	private List <EmployeeSkill> employeeSkills=new ArrayList<>();
	
	public Skill() {
		
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public List<EmployeeSkill> getEmployeeSkills() {
		return employeeSkills;
	}

	public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) {
		this.employeeSkills = employeeSkills;
	}

	public Skill(int skillId, String skillName, List<EmployeeSkill> employeeSkills) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.employeeSkills = employeeSkills;
	}
}
