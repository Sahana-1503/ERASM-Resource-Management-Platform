package com.erasm.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erasm.dto.EmployeeSkillDTO;
import com.erasm.entity.Employee;
import com.erasm.entity.EmployeeSkill;
import com.erasm.entity.Skill;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.exception.EmployeeSkillNotFoundException;
import com.erasm.exception.SkillNotFound;
import com.erasm.mapper.EmployeeSkillMapper;
import com.erasm.repository.EmployeeRepository;
import com.erasm.repository.EmployeeSkillRepository;
import com.erasm.repository.SkillRepository;
import com.erasm.service.EmployeeSkillService;
@Service
public class EmployeeSkillServiceImple implements EmployeeSkillService {
	private final EmployeeSkillRepository employeeSkillRepository;
	private final EmployeeRepository employeeRepository;
	private final SkillRepository skillRepository;
	


	
	public EmployeeSkillServiceImple(EmployeeSkillRepository employeeSkillRepository,
			EmployeeRepository employeeRepository, SkillRepository skillRepository) {
		super();
		this.employeeSkillRepository = employeeSkillRepository;
		this.employeeRepository = employeeRepository;
		this.skillRepository = skillRepository;
	}

	@Override
	public EmployeeSkillDTO addEmployeeSkill(EmployeeSkillDTO employeeSkillDTO) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepository.findById(employeeSkillDTO.getEmployeeId())
				.orElseThrow(()->new EmployeeNotFoundException(
						"Employee not found with id:"+employeeSkillDTO.getEmployeeId()));
		Skill skill=skillRepository.findById(employeeSkillDTO.getSkillId())
				.orElseThrow(()->new SkillNotFound(
						"Skill not found with id:"+employeeSkillDTO.getSkillId()));
		EmployeeSkill employeeSkill=EmployeeSkillMapper.toEntity(employeeSkillDTO);
		employeeSkill.setEmployee(employee);
		employeeSkill.setSkill(skill);
		EmployeeSkill savedEmployeeSkill=employeeSkillRepository.save(employeeSkill);
		return EmployeeSkillMapper.toDTO(savedEmployeeSkill);
		
	}

	@Override
	public EmployeeSkillDTO updateEmployeeSkill(int employeeSkillId, EmployeeSkillDTO employeeSkillDTO) {
		// TODO Auto-generated method stub
		EmployeeSkill employeeSkill=employeeSkillRepository.findById(employeeSkillId)
				.orElseThrow(()->
				new RuntimeException(
						"Employee Skill not found with id:"+employeeSkillId));
		
		Employee employee=employeeRepository.findById(employeeSkillDTO.getEmployeeId())
				.orElseThrow(()->new EmployeeNotFoundException(
						"Employee not found with id:"+employeeSkillDTO.getEmployeeId()));
		Skill skill=skillRepository.findById(employeeSkillDTO.getSkillId())
				.orElseThrow(()->new SkillNotFound(
						"Skill not found with id:"+employeeSkillDTO.getSkillId()));
		employeeSkill.setEmployee(employee);
		employeeSkill.setSkill(skill);

		
		
		employeeSkill.setSkillLevel(employeeSkillDTO.getSkillLevel());
		employeeSkill.setExperience(employeeSkillDTO.getExperience());
		
		
		EmployeeSkill updatedEmployeeSkill=employeeSkillRepository.save(employeeSkill);
		
		return EmployeeSkillMapper.toDTO(updatedEmployeeSkill);
	}

	@Override
	public void deleteEmployeeSkill(int employeeSkillId) {
		// TODO Auto-generated method stub
		if(!employeeSkillRepository.existsById(employeeSkillId)) {
			throw new EmployeeSkillNotFoundException(
					"Employee Skill not found with id:"+employeeSkillId);
		}
		employeeSkillRepository.deleteById(employeeSkillId);
		
	}

	@Override
	public EmployeeSkillDTO getEmployeeSkillById(int employeeSkillId) {
		// TODO Auto-generated method stub
		EmployeeSkill employeeSkill=employeeSkillRepository.findById(employeeSkillId)
				.orElseThrow(()->
				new RuntimeException(
						"Employee Skill not found with id:"+employeeSkillId));
		return EmployeeSkillMapper.toDTO(employeeSkill);
	}

	@Override
	public List<EmployeeSkillDTO> getAllEmployeeSkills() {
		// TODO Auto-generated method stub
		
		return employeeSkillRepository.findAll()
				.stream()
				.map(EmployeeSkillMapper::toDTO)
				.toList();
	}

	
}
