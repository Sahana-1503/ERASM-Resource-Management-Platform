package com.erasm.service;

import java.util.List;

import com.erasm.dto.CertificationDTO;

public interface CertificationService {
	CertificationDTO addCertification(CertificationDTO certificationDTO);
	CertificationDTO updateCertification(int certificationId,CertificationDTO certificationDTO);
	void deleteCertification(int certificationId);
	CertificationDTO getCertificationById(int certificationId);
	List<CertificationDTO> getAllCertifications();
	

}
