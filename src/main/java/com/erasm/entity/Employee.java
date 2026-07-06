package com.erasm.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int employeeId;
	
	
	private String employeeName;
	private String phoneNumber;
	private String department;
	
	
	private String designation;
	
	
	private Double experience;
	private String benchStatus;
	
	@OneToOne
	private User user;
	
	
	
	@OneToMany(mappedBy="employee",
			cascade=CascadeType.ALL,
			fetch=FetchType.LAZY)
	private List<EmployeeSkill> employeeSkills=new ArrayList<>();
	
	@OneToMany(mappedBy="employee",
			cascade=CascadeType.ALL,
			fetch=FetchType.LAZY)
	private List<Certification> certificationss=new ArrayList<>();
	
	@OneToMany(mappedBy="employee",
			cascade=CascadeType.ALL,
			fetch=FetchType.LAZY)
	private List<Allocation> allocations=new ArrayList<>();
	

	
		
	public List<Allocation> getAllocations() {
		return allocations;
	}




	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}




	public Employee()
	{
		
	}




	public int getEmployeeId() {
		return employeeId;
	}




	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}




	public String getEmployeeName() {
		return employeeName;
	}




	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public String getDesignation() {
		return designation;
	}




	public void setDesignation(String designation) {
		this.designation = designation;
	}




	public Double getExperience() {
		return experience;
	}




	public void setExperience(Double experience) {
		this.experience = experience;
	}




	public String getBenchStatus() {
		return benchStatus;
	}




	public void setBenchStatus(String benchStatus) {
		this.benchStatus = benchStatus;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public List<EmployeeSkill> getEmployeeSkills() {
		return employeeSkills;
	}




	public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) {
		this.employeeSkills = employeeSkills;
	}




	public List<Certification> getCertificationss() {
		return certificationss;
	}




	public void setCertificationss(List<Certification> certificationss) {
		this.certificationss = certificationss;
	}



}