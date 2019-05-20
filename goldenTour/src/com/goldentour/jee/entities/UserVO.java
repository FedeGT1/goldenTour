package com.goldentour.jee.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "USER")

public class UserVO {

	private Long ID;
	private String username;
	private String password;
	private String name;
	private String lastname;
	private String address;
	private int cap;
	private String city;
	private Date birthday;
	private String birthPlace;
	private RoleVO role;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, columnDefinition = "integer")
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	@Column(name="USERNAME",length=45,nullable=false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="NAME",length=45,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="LASTNAME",length=45,nullable=false)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name="ADDRESS",length=45,nullable=false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="CAP", nullable=false,columnDefinition = "integer")
	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}
	@Column(name="CITY",length=45,nullable=false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JoinColumn(name="ID")
	@ManyToOne(fetch=FetchType.LAZY)
	public RoleVO getRole() {
		return role;
	}

	public void setRole(RoleVO role) {
		this.role = role;
	}

	@Column(name="PASSWORD",length=45,nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	@Column(name="BIRTHPLACE",length=45,nullable=false)
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		UserVO tmp = (UserVO) obj;
		if (!ID.equals(tmp.ID))
		return false;
	return true;
	}
}
