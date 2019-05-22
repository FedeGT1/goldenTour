package com.goldentour.jee.services;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goldentour.jee.dao.UserDao;
import com.goldentour.jee.entities.User;
import com.goldentour.jee.viewBeans.UserViewBean;

@Service(value = "userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDao")
	UserDao user;

	@Override
	public User find(String fiscalCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByFiscalCode(String fiscalCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserViewBean authorize(String username, String password) throws Exception {

		List<User> users = (List<User>) user.findByUsernameAndPassword(username, password);
		UserViewBean user = null;
		if (users != null && !users.isEmpty()) {
			if (users.size() > 1)
				throw new Exception("More of 1 User");

			return user.getAuthenticate(users.get(0));
		}

		throw new Exception("User not found");
	}

	@Override
	public User find(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User currentUser) {
		// TODO Auto-generated method stub
		
	}

}
