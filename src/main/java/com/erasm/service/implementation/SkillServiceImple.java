package com.erasm.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erasm.dto.SkillDTO;
import com.erasm.entity.Skill;
import com.erasm.exception.SkillAlreadyExistsException;
import com.erasm.exception.SkillNotFound;
import com.erasm.mapper.SkillMapper;
import com.erasm.repository.SkillRepository;
import com.erasm.service.SkillService;

@Service
public class SkillServiceImple implements SkillService  {
	private final SkillRepository skillRepository;
	private static final Logger logger=LoggerFactory.getLogger(SkillServiceImple.class);
	



	@Override
	public SkillDTO addSkill(SkillDTO skillDTO) {
		// TODO Auto-generated method stub
		if(skillRepository.findBySkillName(skillDTO.getSkillName()).isPresent()) {
			throw new SkillAlreadyExistsException(
					"Skill already exists:"+skillDTO.getSkillName());
			
		}
		Skill skill=SkillMapper.toEntity(skillDTO);
		Skill savedSkill=skillRepository.save(skill);
		logger.info("Skill created successfully with ID:{}",savedSkill.getSkillId());
		return SkillMapper.toDTO(savedSkill) ;
	}

	@Override
	public SkillDTO updateSkill(int skillId, SkillDTO skillDTO) {
		// TODO Auto-generated method stub
		Skill skill=skillRepository.findById(skillId)
				.orElseThrow(()->
				new SkillNotFound(
						"Skill not found with id:"+skillId));
		skill.setSkillName(skillDTO.getSkillName());
		Skill updatedSkill=skillRepository.save(skill);
		logger.info("Skill updated successfully with ID:{}",updatedSkill.getSkillId());
		
		return SkillMapper.toDTO(updatedSkill);
	}

	@Override
	public void deleteSkill(int skillId) {
		if(!skillRepository.existsById(skillId)) {
			logger.warn("Skill not found with ID:{}",skillId);
			throw new SkillNotFound(
					"Skill not found with id:"+skillId);
		}
		skillRepository.deleteById(skillId);
		logger.info("Skill deleted successfully with ID:{}",skillId);
		


		
	}

	@Override
	public SkillDTO getSkillById(int skillId) {
		// TODO Auto-generated method stub
		Skill skill=skillRepository.findById(skillId)
				.orElseThrow(()->
				new SkillNotFound("Skill not found with id:"+skillId));
		return SkillMapper.toDTO(skill);
	}

	@Override
	public List<SkillDTO> getAllSkills() {
		// TODO Auto-generated method stub
		return skillRepository.findAll()
				.stream()
				.map(SkillMapper::toDTO)
				.toList();
	}

	public SkillServiceImple(SkillRepository skillRepository) {
		super();
		this.skillRepository = skillRepository;
	}
	

}
