package com.goldentour.jee.utils;

public class ConfigBean {
	
	private String databaseDriver;
	private String databaseUrl;
	private String databaseUsername;
	private String databasePassword;

	private String pesistenceUnitName;
	
	public String getDatabaseDriver() {
		return databaseDriver;
	}
	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}
	public String getDatabaseUrl() {
		return databaseUrl;
	}
	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}
	public String getDatabaseUsername() {
		return databaseUsername;
	}
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	public String getPesistenceUnitName() {
		return pesistenceUnitName;
	}
	public void setPesistenceUnitName(String pesistenceUnitName) {
		this.pesistenceUnitName = pesistenceUnitName;
	}

}
