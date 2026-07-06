package com.erasm.mapper;

import com.erasm.dto.AllocationDTO;
import com.erasm.entity.Allocation;

public class AllocationMapper {
	public static AllocationDTO toDTO(Allocation allocation) {
		AllocationDTO dto=new AllocationDTO();
		
		dto.setAllocationId(allocation.getAllocationId());
		
		if(allocation.getEmployee()!=null) {
			dto.setEmployeeId(allocation.getEmployee().getEmployeeId());
			
		}
		
		if(allocation.getProject()!=null) {
			dto.setProjectId(allocation.getProject().getProjectId());
		}
		
		dto.setAllocationPercentage(allocation.getAllocationPercentage());
		dto.setAllocationDate(allocation.getAllocationDate());
		dto.setReleaseDate(allocation.getReleaseDate());
		dto.setStatus(allocation.getStatus());
		
		return dto;
	}
	
	public static Allocation toEntity(AllocationDTO dto) {
		
		Allocation allocation=new Allocation();
		allocation.setAllocationId(dto.getAllocationId());
		allocation.setAllocationPercentage(dto.getAllocationPercentage());
		allocation.setReleaseDate(dto.getReleaseDate());
		allocation.setStatus(dto.getStatus());
		allocation.setAllocationDate(dto.getAllocationDate());

		
	
		return allocation;
	}
}
	