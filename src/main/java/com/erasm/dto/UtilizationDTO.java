package com.erasm.dto;

public class UtilizationDTO {
	private int employeeId;
	private String employeeName;
	private double billablePercentage;
	private double benchPercentage;
	public UtilizationDTO() {
		
	}
	public UtilizationDTO(int employeeId, String employeeName, double billablePercentage, double benchPercentage) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.billablePercentage = billablePercentage;
		this.benchPercentage = benchPercentage;
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
	public double getBillablePercentage() {
		return billablePercentage;
	}
	public void setBillablePercentage(double billablePercentage) {
		this.billablePercentage = billablePercentage;
	}
	public double getBenchPercentage() {
		return benchPercentage;
	}
	public void setBenchPercentage(double benchPercentage) {
		this.benchPercentage = benchPercentage;
	}
	

}
