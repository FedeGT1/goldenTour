package com.goldentour.jee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_role")
	private Long idrole;
	
	@Column(name = "rolename", nullable = false)
	private String rolename;
	
	
	public Long getIdrole() {
		return idrole;
	}
	public void setIdrole(Long idrole) {
		this.idrole = idrole;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
