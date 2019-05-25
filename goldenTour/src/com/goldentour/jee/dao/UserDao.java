package com.goldentour.jee.dao;

import java.util.List;

import com.goldentour.jee.entities.User;

public interface UserDao extends GenericDao<User>{
	
	User findByUsernameAndPassword(String username, String password);
	
	List<User> findByNameAndLastname(String name, String lastname);

}
