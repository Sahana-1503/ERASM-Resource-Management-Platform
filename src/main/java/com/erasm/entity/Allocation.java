package com.erasm.entity;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name="allocations")
public class Allocation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="allocation_id")
	private int allocationId;
	
	@Column(name="allocation_percentage",nullable=false)
	private Integer allocationPercentage;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="allocation_date",nullable=false)
	private LocalDate allocationDate;
		
	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private LocalDate releaseDate;
	
	@Column(nullable=false)
	private String status;
	public Allocation() {
		
	}

	
	

	public Allocation(int allocationId, Integer allocationPercentage, Employee employee, Project project,
			LocalDate allocationDate, LocalDate releaseDate, String status) {
		super();
		this.allocationId = allocationId;
		this.allocationPercentage = allocationPercentage;
		this.employee = employee;
		this.project = project;
		this.allocationDate = allocationDate;
		this.releaseDate = releaseDate;
		this.status = status;
	}




	public int getAllocationId() {
		return allocationId;
	}




	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}




	public Integer getAllocationPercentage() {
		return allocationPercentage;
	}




	public void setAllocationPercentage(Integer allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}




	public Employee getEmployee() {
		return employee;
	}




	public void setEmployee(Employee employee) {
		this.employee = employee;
	}




	public Project getProject() {
		return project;
	}




	public void setProject(Project project) {
		this.project = project;
	}




	public LocalDate getAllocationDate() {
		return allocationDate;
	}




	public void setAllocationDate(LocalDate allocationDate) {
		this.allocationDate = allocationDate;
	}




	public LocalDate getReleaseDate() {
		return releaseDate;
	}




	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}
}


	