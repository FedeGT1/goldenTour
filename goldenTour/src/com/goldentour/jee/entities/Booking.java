package com.goldentour.jee.entities;

import java.io.Serializable;
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
@Table(name = "booking")
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination")
	private Destination idDestination;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "person_number", nullable = false)
	private int personNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_transport")
	private Transport idTransport;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_accomodation")
	private Accomodation idAccomodation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User User;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tourOperator")
	private User TourOperator;
	
	private int price;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Destination getIdDestination() {
		return idDestination;
	}

	public void setIdDestination(Destination idDestination) {
		this.idDestination = idDestination;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}

	public Transport getIdTransport() {
		return idTransport;
	}

	public void setIdTransport(Transport idTransport) {
		this.idTransport = idTransport;
	}

	public Accomodation getIdAccomodation() {
		return idAccomodation;
	}

	public void setIdAccomodation(Accomodation idAccomodation) {
		this.idAccomodation = idAccomodation;
	}
	


	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public User getTourOperator() {
		return TourOperator;
	}

	public void setTourOperator(User tourOperator) {
		TourOperator = tourOperator;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Booking  tmp = (Booking) obj;
		if (!id.equals(tmp.getId()))
		return false;
	return true;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


}
