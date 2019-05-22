package com.goldentour.jee.viewBeans;

import com.goldentour.jee.entities.User;

public class UserViewBean {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String address;
    private String city;
    private String birthday;
    private String birthplace;
    private String cap;
    private String role;

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
		// TODO Auto-generated method stub
		return null;
	}


   /* public UserViewBean getAuthenticate(User u) {
       // this.email = u.getEmail();
        this.username = u.getUsername();
        //this.role = u.getRole().getRolename();
    }*/

} 