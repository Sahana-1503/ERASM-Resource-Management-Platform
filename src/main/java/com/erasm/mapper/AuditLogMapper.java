package com.erasm.mapper;

import com.erasm.dto.AuditLogDTO;
import com.erasm.entity.AuditLog;

public class AuditLogMapper {
	public static AuditLogDTO toDTO(AuditLog auditLog) {
		
		
		AuditLogDTO dto=new AuditLogDTO();
		dto.setAuditId(auditLog.getAuditId());
		dto.setEntityName(auditLog.getEntityName());
		dto.setAction(auditLog.getAction());
		dto.setCreatedBy(auditLog.getCreatedBy());
		dto.setModifiedBy(auditLog.getModifiedBy());
		dto.setCreatedDate(auditLog.getCreatedDate());
		dto.setModifiedDate(auditLog.getModifiedDate());





		
		return dto;
		
	}
	public static AuditLog toEntity(AuditLogDTO dto) {
		AuditLog auditLog=new AuditLog();
		auditLog.setAuditId(dto.getAuditId());
		auditLog.setEntityName(dto.getEntityName());
		auditLog.setAction(dto.getAction());
		auditLog.setCreatedBy(dto.getCreatedBy());
		auditLog.setModifiedBy(dto.getModifiedBy());
		auditLog.setCreatedDate(dto.getCreatedDate());
		auditLog.setModifiedDate(dto.getModifiedDate());
		return auditLog;
	}
		

}
