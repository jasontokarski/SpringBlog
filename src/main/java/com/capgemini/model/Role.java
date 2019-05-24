package com.capgemini.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	private Long id;
	
	@Column(name = "role")
	private String role;
	
	public Role() {
	}

	public Role(Long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
