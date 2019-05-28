package com.goldentour.jee.viewBeans;

public class BookingViewBean {

	private long id;
	private String description;
	private int personNumber;
	private String startDate;
	private String endDate;
	private double price;
	private long user;
	private long transport;
	private long destination;
	private long accomodation;
	private long tourOperator;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long l) {
		this.user = l;
	}

	public long getTransport() {
		return transport;
	}

	public void setTransport(long transport) {
		this.transport = transport;
	}

	public long getDestination() {
		return destination;
	}

	public void setDestination(long destination) {
		this.destination = destination;
	}

	public long getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(long accomodation) {
		this.accomodation = accomodation;
	}

	public long getTourOperator() {
		return tourOperator;
	}

	public void setTourOperator(long tourOperator) {
		this.tourOperator = tourOperator;
	}
}