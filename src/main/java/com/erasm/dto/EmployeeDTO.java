package com.erasm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDTO {
	private int employeeId;
	
	@NotBlank(message="Employee name is required")
	private String employeeName;
	
	@NotBlank(message="Phonenumber is required")
	private String phoneNumber;
	
	@NotBlank(message="Department is required")
	private String department;
	
	@NotBlank(message="Designation is required")
	private String designation;
	
	@NotNull
	private Double experience;
	
	private String benchStatus;
	
	private int userId;




	
	public EmployeeDTO() {
		
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





	public int getUserId() {
		return userId;
	}





	public void setUserId(int userId) {
		this.userId = userId;
	}





}