package com.goldentour.jee.viewBeans;

import com.goldentour.jee.entities.User;

import java.util.Date;

public class UserViewBean {
    private long iduser;
    private String email;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String address;
    private String city;
    private Date birthday;
    private String birthplace;
    private int cap;
    private String role;

    public long getId() {
		return iduser;
	}

	public void setId(long iduser) {
		this.iduser = iduser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public UserViewBean getAuthenticate(User user) {
		this.iduser = user.getIduser();
		this.username = user.getUsername();
		this.role = user.getRole().getRolename();
		this.name = user.getName();
		this.lastname = user.getLastname();
		return this;
	}

	public UserViewBean fromEntity(User user) {
        this.iduser = user.getIduser();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.setBirthday(user.getBirthday());
        this.address = user.getAddress();
        this.city = user.getCity();
        this.cap = user.getCap();
        this.birthplace = user.getBirthplace();
        return this;
    }

} 