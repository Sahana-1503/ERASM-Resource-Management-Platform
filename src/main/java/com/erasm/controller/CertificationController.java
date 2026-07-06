package com.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.CertificationDTO;
import com.erasm.service.CertificationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/certifications")
public class CertificationController {
	private final CertificationService certificationService;

	
	public CertificationController(CertificationService certificationService) {
		super();
		this.certificationService = certificationService;
	}

	@PostMapping
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<CertificationDTO> addCertification(@Valid @RequestBody CertificationDTO certificationDTO){
		CertificationDTO savedCertification=certificationService.addCertification(certificationDTO);
		return new ResponseEntity<>(savedCertification,HttpStatus.CREATED);
		
		}
	
	@GetMapping
	public ResponseEntity<List<CertificationDTO>> getAllCertifications(){
		return ResponseEntity.ok(certificationService.getAllCertifications());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CertificationDTO> getCertificationById(@PathVariable int id){
		return ResponseEntity.ok(certificationService.getCertificationById(id));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<CertificationDTO> updateCertification(
			@PathVariable int id,
			@Valid @RequestBody CertificationDTO certificationDTO){
		return ResponseEntity.ok(certificationService.updateCertification(id, certificationDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCertification(@PathVariable int id){
		certificationService.deleteCertification(id);
		return ResponseEntity.ok("Certification deleted Successfully");
	}
}
	
