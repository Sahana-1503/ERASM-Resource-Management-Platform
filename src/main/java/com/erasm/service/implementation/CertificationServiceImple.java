package com.erasm.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erasm.dto.CertificationDTO;
import com.erasm.entity.Certification;
import com.erasm.entity.Employee;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.mapper.CertificationMapper;
import com.erasm.repository.CertificationRepository;
import com.erasm.repository.EmployeeRepository;
import com.erasm.service.CertificationService;

@Service
public class CertificationServiceImple implements CertificationService {
	
	private final EmployeeRepository employeeRepository ;
	private final CertificationRepository certificationRepository;
	private static final Logger logger=LoggerFactory.getLogger(CertificationServiceImple.class);
	


	


	
	

	public CertificationServiceImple(EmployeeRepository employeeRepository,
			CertificationRepository certificationRepository) {

		this.employeeRepository = employeeRepository;
		this.certificationRepository = certificationRepository;
	}

	@Override
	public CertificationDTO addCertification(CertificationDTO certificationDTO) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepository.findById(certificationDTO.getEmployeeId())
				.orElseThrow(()->new EmployeeNotFoundException(
						"Employee not found with id:"+certificationDTO.getEmployeeId()));
		
		Certification certification=CertificationMapper.toEntity(certificationDTO);
		certification.setEmployee(employee);
		
		Certification savedCertification=certificationRepository.save(certification);
		logger.info("Certification created successfully with ID:{}",savedCertification.getCertificationId());
		return CertificationMapper.toDTO(savedCertification);
		
	}

	@Override
	public CertificationDTO updateCertification(int certificationId, CertificationDTO certificationDTO) {
		// TODO Auto-generated method stub
		Certification certification=certificationRepository.findById(certificationId)
				.orElseThrow(()->
				new RuntimeException(
						"Certification not found with id:"+certificationId));
		
		Employee employee=employeeRepository.findById(certificationDTO.getEmployeeId())
				.orElseThrow(()->new EmployeeNotFoundException(
						"Employee not found with id:"+certificationDTO.getEmployeeId()));
		certification.setEmployee(employee);
		certification.setIssuingOrganization(certificationDTO.getIssuingOrganization());
		certification.setCertificationName(certificationDTO.getCertificationName());
		

		
		
				
		Certification updatedCertification=certificationRepository.save(certification);
		logger.info("Certification updated successfully with ID:{}",updatedCertification.getCertificationId());
		
		
		return CertificationMapper.toDTO(updatedCertification);
	}

	@Override
	public void deleteCertification(int certificationId) {
		// TODO Auto-generated method stub
		if(!certificationRepository.existsById(certificationId)) {
			logger.warn("Certificate not found with ID:{}",certificationId);
			throw new RuntimeException(
					"Certification not found with id:"+certificationId);
		}
		certificationRepository.deleteById(certificationId);
		logger.info("Certificate deleted successfully with ID:{}",certificationId);
		

		
	}

	@Override
	public CertificationDTO getCertificationById(int certificationId) {
		// TODO Auto-generated method stub
		Certification certification=certificationRepository.findById(certificationId)
				.orElseThrow(()->
				new RuntimeException(
						"Certification not found with id:"+certificationId));
		return CertificationMapper.toDTO(certification);
	}

	@Override
	public List<CertificationDTO> getAllCertifications() {
		// TODO Auto-generated method stub
		
		return certificationRepository.findAll()
				.stream()
				.map(CertificationMapper::toDTO)
				.toList();
	}

	


}
