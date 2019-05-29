package com.goldentour.jee.services;

import java.util.List;

import com.goldentour.jee.entities.User;
import com.goldentour.jee.exception.AuthenticationException;
import com.goldentour.jee.viewBeans.UserViewBean;

public interface UserService {

	UserViewBean find(int idUser) throws AuthenticationException;

	UserViewBean authorize(String username, String password) throws Exception ;

	User update(UserViewBean currentUser) throws AuthenticationException;
	
	List<UserViewBean> returnClients(String name, String lastname) throws AuthenticationException;
	
	User register(UserViewBean user) throws AuthenticationException;

}


