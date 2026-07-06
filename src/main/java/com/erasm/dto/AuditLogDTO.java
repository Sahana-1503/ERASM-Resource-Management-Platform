package com.erasm.dto;

import java.time.LocalDateTime;

public class AuditLogDTO {
	private int auditId;
	private String entityName;
	private String action;
	private String createdBy;
	private String modifiedBy;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	
	public AuditLogDTO() {
		
	}


	public AuditLogDTO(int auditId, String entityName, String action, String createdBy, String modifiedBy,
			LocalDateTime createdDate, LocalDateTime modifiedDate) {
		super();
		this.auditId = auditId;
		this.entityName = entityName;
		this.action = action;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}


	public int getAuditId() {
		return auditId;
	}


	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}


	public String getEntityName() {
		return entityName;
	}


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}