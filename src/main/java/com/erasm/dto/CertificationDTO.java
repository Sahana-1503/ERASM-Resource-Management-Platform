package com.erasm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CertificationDTO {
	private int certificationId;
	
	@NotBlank(message="Certification Name is required")
	private String certificationName;
	
	@NotBlank(message="Issued Organization is required")
	private String issuingOrganization;
	
	@NotNull(message="Employee ID is required")
	private Integer employeeId;
	
	
	public CertificationDTO() {
		
	}


	public int getCertificationId() {
		return certificationId;
	}


	public void setCertificationId(int certificationId) {
		this.certificationId = certificationId;
	}


	public String getCertificationName() {
		return certificationName;
	}


	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}


	
	

	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public String getIssuingOrganization() {
		return issuingOrganization;
	}


	public void setIssuingOrganization(String issuingOrganization) {
		this.issuingOrganization = issuingOrganization;
	}


	
}
