package com.erasm.entity;
import jakarta.persistence.*;

@Entity
@Table(name="resource_requests")
public class ResourceRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_id")
	private int requestId;
	
	@Column(name="required_skill",nullable=false)
	private String requiredSkill;
	
	@Column(name="required_count",nullable=false)
	private Integer requiredCount;
	
	@Column(name="status",nullable=false)
	private String status;                   //draft  submitted  approved  rejected  aloocated  completed
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	public ResourceRequest() {
		
	}

	public ResourceRequest(int requestId, String requiredSkill, Integer requiredCount, String status, Project project) {
		super();
		this.requestId = requestId;
		this.requiredSkill = requiredSkill;
		this.requiredCount = requiredCount;
		this.status = status;
		this.project = project;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	

}
