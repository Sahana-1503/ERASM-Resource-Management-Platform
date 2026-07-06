package com.erasm.entity;
import jakarta.persistence.*;
@Entity
@Table(name="certifications")
public class Certification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="certification_id")
	private int certificationId;
	
	@Column(name="certification_name",nullable=false)
	private String certificationName;
	
	@Column(name="issuing_organization",nullable=false)
	private String issuingOrganization;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public Certification() {
		
	}

	public Certification(int certificationId, String certificationName, String issuingOrganization,
			Employee employee) {
		super();
		this.certificationId = certificationId;
		this.certificationName = certificationName;
		this.issuingOrganization = issuingOrganization;
		this.employee = employee;
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

	public String getIssuingOrganization() {
		return issuingOrganization;
	}

	public void setIssuingOrganization(String issuingOrganization) {
		this.issuingOrganization = issuingOrganization;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

		

}
