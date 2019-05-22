package com.goldentour.jee.services;

import com.goldentour.jee.entities.User;

public interface UserService {

	User find(String fiscalCode);

	void saveUser(User user);

	User findByFiscalCode(String fiscalCode);

}
