package com.goldentour.jee.viewBeans;

public class DestinationViewBean {

	private long idDestination;
	private String name;
	private String county;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public long getIdDestination() {
		return idDestination;
	}
	public void setIdDestination(long idDestination) {
		this.idDestination = idDestination;
	}
}