package com.erasm.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erasm.dto.AuditLogDTO;
import com.erasm.entity.AuditLog;
import com.erasm.mapper.AuditLogMapper;
import com.erasm.repository.AuditLogRepository;
import com.erasm.service.AuditLogService;

@Service
public class AuditLogServiceImple implements AuditLogService {
	private final AuditLogRepository auditLogRepository;

	public AuditLogServiceImple(AuditLogRepository auditLogRepository) {
		super();
		this.auditLogRepository = auditLogRepository;
	}

	@Override
	public AuditLogDTO addAuditLog(AuditLogDTO auditLogDTO) {
		// TODO Auto-generated method stub
		AuditLog auditLog=AuditLogMapper.toEntity(auditLogDTO);
		AuditLog savedAudit=auditLogRepository.save(auditLog);
		return AuditLogMapper.toDTO(savedAudit);
	}

	@Override
	public List<AuditLogDTO> getAllAuditLogs() {
		// TODO Auto-generated method stub
		return auditLogRepository.findAll()
				.stream()
				.map(AuditLogMapper::toDTO)
				.toList()
		
		;
	}

	@Override
	public AuditLogDTO getAuditLogById(int auditId) {
		// TODO Auto-generated method stub
		AuditLog auditLog=auditLogRepository.findById(auditId)
				.orElseThrow(()->
				new RuntimeException(
						"Audit Log not found with id:"+auditId));
		return AuditLogMapper.toDTO(auditLog);

	
	}

	@Override
	public void deleteAuditLog(int auditId) {
		// TODO Auto-generated method stub
		if(!auditLogRepository.existsById(auditId)) {
			throw new RuntimeException("Audit Log not found with id:"+auditId);
		}
		auditLogRepository.deleteById(auditId);
	}
	

}
