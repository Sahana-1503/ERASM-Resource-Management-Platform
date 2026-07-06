package com.erasm.entity;
import jakarta.persistence.*;
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userid;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@OneToOne(mappedBy="user")
	private Employee employee;
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	public User() {
		
	}

	
	public User(int userid, String email, String password, Employee employee, Role role) {
		super();
		this.userid = userid;
		this.email = email;
		this.password = password;
		this.employee = employee;
		this.role = role;
	}


	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
