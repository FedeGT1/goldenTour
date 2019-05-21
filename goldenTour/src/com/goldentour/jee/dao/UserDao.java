package com.goldentour.jee.dao;

import java.util.List;

import com.goldentour.jee.entities.User;

public interface UserDao extends GenericDao<User>{
	
	List<User> findByUsernameAndPassword(String username, String password);

}
