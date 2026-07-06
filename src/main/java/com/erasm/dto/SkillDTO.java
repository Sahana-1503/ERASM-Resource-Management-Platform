package com.erasm.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillDTO {
	private int skillId;
	
	@NotBlank(message="Skill Name is required")
	private String skillName;
	
	public SkillDTO() {
		
	}

	public SkillDTO(int skillId, @NotBlank(message = "Skill Name is required") String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
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

	


}
