package com.erasm.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name="audit_logs")
public class AuditLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="audit_id")
	private int auditId;
	

	@Column(name="entity_name",nullable=false)
	private String entityName;
	
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




	public AuditLog(int auditId, String entityName, String action, String createdBy, String modifiedBy,
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




	@Column(name="action",nullable=false)
	private String action;
	
	@Column(name="created_by",nullable=false)
	private String createdBy;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@Column(name="created_date",nullable=false)
	private LocalDateTime createdDate;
	
	@Column(name="modified_date")
	private LocalDateTime modifiedDate;
	
	
	
	
	public AuditLog() {
		
		
	}
}

	