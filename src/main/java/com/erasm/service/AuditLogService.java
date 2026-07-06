package com.erasm.service;

import java.util.List;

import com.erasm.dto.AuditLogDTO;

public interface AuditLogService {
	AuditLogDTO addAuditLog(AuditLogDTO auditLogDTO);
	List<AuditLogDTO> getAllAuditLogs();
	AuditLogDTO getAuditLogById(int auditId);
	void deleteAuditLog(int auditId);
	
	

}
