package com.goldentour.jee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="accomodation")
public class Accomodation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="name", nullable=false)
	private String name;

	@Column(name="address", nullable=false)
	private String address;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="destination")
	private
	Destination idDestination;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accomodation_type")
	private 	AccomodationType idType;

	@Column(name="telephone")
	private int telephone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AccomodationType getIdType() {
		return idType;
	}

	public void setIdType(AccomodationType idType) {
		this.idType = idType;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Destination getIdDestination() {
		return idDestination;
	}

	public void setIdDestination(Destination idDestination) {
		this.idDestination = idDestination;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Accomodation tmp = (Accomodation) obj;
		if (!id.equals(tmp.getId()))
		return false;
	return true;
	}



}
