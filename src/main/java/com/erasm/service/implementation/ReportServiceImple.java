package com.erasm.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erasm.dto.AllocationDTO;
import com.erasm.dto.SkillReportDTO;
import com.erasm.dto.UtilizationDTO;
import com.erasm.entity.EmployeeSkill;
import com.erasm.entity.Skill;
import com.erasm.mapper.AllocationMapper;
import com.erasm.repository.AllocationRepository;
import com.erasm.repository.EmployeeSkillRepository;
import com.erasm.repository.SkillRepository;
import com.erasm.service.ReportService;
import com.erasm.service.UtilizationService;

@Service
public class ReportServiceImple implements ReportService{
	private final SkillRepository skillRepository;
	private final EmployeeSkillRepository employeeSkillRepository;
	private final AllocationRepository allocationRepository;
	private final UtilizationService utilizationService;
	
		public ReportServiceImple(SkillRepository skillRepository, EmployeeSkillRepository employeeSkillRepository,
			AllocationRepository allocationRepository, UtilizationService utilizationService) {
		super();
		this.skillRepository = skillRepository;
		this.employeeSkillRepository = employeeSkillRepository;
		this.allocationRepository = allocationRepository;
		this.utilizationService = utilizationService;
	}



		@Override
	public List<SkillReportDTO> getSkillReport() {
		// TODO Auto-generated method stub
		List<SkillReportDTO> report=new ArrayList<>();
		List<Skill> skills=skillRepository.findAll();
		for(Skill skill:skills) {
			SkillReportDTO dto=new SkillReportDTO();
			dto.setSkillName(skill.getSkillName());
			List<String> employeeNames=new ArrayList<>();
			List<EmployeeSkill> employeeSkills=employeeSkillRepository.findAll();
			for(EmployeeSkill employeeSkill:employeeSkills) {
				if(employeeSkill.getSkill().getSkillId()==skill.getSkillId()) {
					employeeNames.add(employeeSkill.getEmployee().getEmployeeName());
				}
				
			}
			dto.setEmployeeNames(employeeNames);
			report.add(dto);
		}
			return report;
			
			
			}
	
	
	
	@Override
	public List<UtilizationDTO> getUtilizationReport() {
		// TODO Auto-generated method stub
		return utilizationService.getAllEmployeesUtilization();
		
	}
	@Override
	public List<AllocationDTO> getProjectAllocationReport() {
		// TODO Auto-generated method stub
		return allocationRepository.findAll()
				.stream()
				.map(AllocationMapper::toDTO)
				.toList();
	}
	

}
