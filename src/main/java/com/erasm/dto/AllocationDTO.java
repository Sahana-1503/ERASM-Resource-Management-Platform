package com.erasm.dto;

import java.time.LocalDate;

public class AllocationDTO {
	private int allocationId;
	private int employeeId;
	private int projectId;
	private Integer allocationPercentage;
	private LocalDate allocationDate;
	private LocalDate releaseDate;
	private String status;
	
	public AllocationDTO() {
		
	}

	public AllocationDTO(int allocationId, int employeeId, int projectId, Integer allocationPercentage,
			LocalDate allocationDate, LocalDate releaseDate, String status) {
		super();
		this.allocationId = allocationId;
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.allocationPercentage = allocationPercentage;
		this.allocationDate = allocationDate;
		this.releaseDate = releaseDate;
		this.status = status;
	}

	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Integer getAllocationPercentage() {
		return allocationPercentage;
	}

	public void setAllocationPercentage(Integer allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}

	public LocalDate getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(LocalDate allocationDate) {
		this.allocationDate = allocationDate;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
	