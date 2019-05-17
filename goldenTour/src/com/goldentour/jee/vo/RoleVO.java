package com.goldentour.jee.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLE")

public class RoleVO {

	
	private Long ID;
	
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID",nullable=false,columnDefinition="integer")
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	@Column(name="NAME",length=45,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		RoleVO tmp = (RoleVO) obj;
		if (!ID.equals(tmp.ID))
		return false;
	return true;
	}

}
