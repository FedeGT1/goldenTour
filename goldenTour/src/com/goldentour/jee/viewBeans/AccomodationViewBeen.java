package com.goldentour.jee.viewBeans;

public class AccomodationViewBeen {

	private String id;

	private String name;

	private String address;

	private String telephone;

	private String price;

	private String accomodationType;

	private String destination;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = "" + telephone;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = "" + price;
	}

	public String getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(String string) {
		this.accomodationType = string;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String string) {
		this.destination = string;
	}

	public String getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = "" + id;
	}

}
