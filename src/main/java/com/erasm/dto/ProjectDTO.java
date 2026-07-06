package com.erasm.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectDTO {
	
	
	private int projectId;
	
	@NotBlank(message="Project name is required")
	private String projectName;
	
	@NotBlank(message="Client name is required")
	private String clientName;
	@NotNull(message="Start Date is required")
	private LocalDate startDate;	
	
	private LocalDate endDate;
	
	@NotBlank(message="Technology Stack is required")
	private String technologyStack;

	@NotNull(message="Budget is required")
	private Integer budget;	
	

	
	
	public ProjectDTO() {
		
	}




	public int getProjectId() {
		return projectId;
	}




	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}




	public String getProjectName() {
		return projectName;
	}




	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}




	public String getClientName() {
		return clientName;
	}




	public void setClientName(String clientName) {
		this.clientName = clientName;
	}




	public @NotNull(message = "Start Date is required") LocalDate getStartDate() {
		return startDate;
	}




	public void setStartDate(LocalDate localDate) {
		this.startDate = localDate;
	}




	public LocalDate getEndDate() {
		return endDate;
	}




	public void setEndDate(LocalDate localDate) {
		this.endDate = localDate;
	}




	public String getTechnologyStack() {
		return technologyStack;
	}




	public void setTechnologyStack(String technologyStack) {
		this.technologyStack = technologyStack;
	}




	public Integer getBudget() {
		return budget;
	}




	public void setBudget(Integer budget) {
		this.budget = budget;
	}



}