package com.erasm.entity;
import jakarta.persistence.*;
@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private int roleID;
	
	@Column(name="role_name",nullable=false,unique=true)
	private String roleName;
	
	public Role() {
		
	}
	public Role(int roleID,String roleName) {
		this.roleID=roleID;
		this.roleName=roleName;
		
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
