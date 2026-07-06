package com.erasm.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="projects")

public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="project_id",nullable=false)
	private int projectId;
	
	@Column(name="project_name",nullable=false)
	private String projectName;
	
	@Column(name="client_name",nullable=false)
	private String clientName;
	
	@Column(name="start_date",nullable=false)
	private @NotNull(message = "Start Date is required") @NotNull(message = "Start Date is required") LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="technology_stack",nullable=false)
	private String technologyStack;
	
	@Column(name="budget",nullable=false)
	private int budget;
	
	@OneToMany(mappedBy="project")
	private List<ResourceRequest> resourceRequests;
	
	@OneToMany(mappedBy="project")
	private List<Allocation> allocations;
	
	public Project() {
		
	}

	
	public Project(int projectId, String projectName, String clientName,
			@NotNull(message = "Start Date is required") @NotNull(message = "Start Date is required") LocalDate startDate,
			LocalDate endDate, String technologyStack, int budget, List<ResourceRequest> resourceRequests,
			List<Allocation> allocations) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.clientName = clientName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.technologyStack = technologyStack;
		this.budget = budget;
		this.resourceRequests = resourceRequests;
		this.allocations = allocations;
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


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	

	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public String getTechnologyStack() {
		return technologyStack;
	}


	public void setTechnologyStack(String technologyStack) {
		this.technologyStack = technologyStack;
	}


	public int getBudget() {
		return budget;
	}


	public void setBudget(int budget) {
		this.budget = budget;
	}


	public List<ResourceRequest> getResourceRequests() {
		return resourceRequests;
	}


	public void setResourceRequests(List<ResourceRequest> resourceRequests) {
		this.resourceRequests = resourceRequests;
	}


	public List<Allocation> getAllocations() {
		return allocations;
	}


	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}


}