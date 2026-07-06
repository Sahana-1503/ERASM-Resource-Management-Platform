package com.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.AuditLogDTO;
import com.erasm.service.AuditLogService;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {
	private final AuditLogService auditLogService;

	public AuditLogController(AuditLogService auditLogService) {
		super();
		this.auditLogService = auditLogService;
	}
	@PostMapping
	public ResponseEntity<AuditLogDTO> addAuditLog(@RequestBody AuditLogDTO auditLogDTO){
		AuditLogDTO savedAudit=auditLogService.addAuditLog(auditLogDTO);
		return new ResponseEntity<>(savedAudit,HttpStatus.CREATED);
	}
	@GetMapping
	@PreAuthorize("hasRole('AUDITOR')")
	public ResponseEntity<List<AuditLogDTO>> getAllAuditLogs(){
		return ResponseEntity.ok(auditLogService.getAllAuditLogs());
		
	}
	@GetMapping("{id}")
	@PreAuthorize("hasRole('AUDITOR')")
	public ResponseEntity<AuditLogDTO> getAuditLogById(@PathVariable int id){
		return ResponseEntity.ok(auditLogService.getAuditLogById(id));
		
	}

	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('AUDITOR')")
	public ResponseEntity<String> deleteAuditLog(@PathVariable int id){
		auditLogService.deleteAuditLog(id);
		return ResponseEntity.ok("Audit Log deleted Successfully");
	}
	

}
