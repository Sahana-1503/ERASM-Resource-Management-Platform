package com.erasm.service;

import java.util.List;

import com.erasm.dto.SkillDTO;

public interface SkillService {
	SkillDTO addSkill(SkillDTO skillDTO);
	SkillDTO updateSkill(int skillId,SkillDTO skillDTO);
	void deleteSkill(int skillId);
	SkillDTO getSkillById(int skillId);
	List<SkillDTO> getAllSkills();

}
