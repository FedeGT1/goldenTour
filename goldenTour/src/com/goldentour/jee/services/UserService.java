package com.goldentour.jee.services;

import java.util.List;

import com.goldentour.jee.entities.User;
import com.goldentour.jee.exception.AuthenticationException;
import com.goldentour.jee.viewBeans.UserViewBean;

public interface UserService {

	UserViewBean find(int idUser);

	void saveUser(User user);

	User findByFiscalCode(String fiscalCode);

	User findByID();

	UserViewBean authorize(String username, String password) throws Exception ;

	User find(String fiscalCode);

	User update(UserViewBean currentUser);
	
	List<UserViewBean> returnClients(String name, String lastname) throws AuthenticationException;
	
	boolean register(UserViewBean user);

}


