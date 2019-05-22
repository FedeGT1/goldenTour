package com.goldentour.jee.services;

import com.goldentour.jee.entities.User;
import com.goldentour.jee.viewBeans.UserViewBean;

public interface UserService {

	User find(int idUser);

	void saveUser(User user);

	User findByFiscalCode(String fiscalCode);

	User findByID();

	UserViewBean authorize(String username, String password) throws Exception ;

	void update(User currentUser);
}


