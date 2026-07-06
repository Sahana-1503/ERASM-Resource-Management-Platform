package com.erasm.dto;

public class ResourceRequestDTO {
	private int requestId;
	private String requiredSkill;
	private Integer requiredCount;
	private String status;
	private int projectId;
	
	public ResourceRequestDTO() {
		
	}

	public ResourceRequestDTO(int requestId, String requiredSkill, Integer requiredCount, String status,
			int projectId) {
		super();
		this.requestId = requestId;
		this.requiredSkill = requiredSkill;
		this.requiredCount = requiredCount;
		this.status = status;
		this.projectId = projectId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequiredSkill() {
		return requiredSkill;
	}

	public void setRequiredSkill(String requiredSkill) {
		this.requiredSkill = requiredSkill;
	}

	public Integer getRequiredCount() {
		return requiredCount;
	}

	public void setRequiredCount(Integer requiredCount) {
		this.requiredCount = requiredCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	

	}

