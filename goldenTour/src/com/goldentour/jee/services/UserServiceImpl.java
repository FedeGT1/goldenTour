package com.goldentour.jee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goldentour.jee.dao.UserDao;
import com.goldentour.jee.entities.User;
import com.goldentour.jee.exception.AuthenticationException;
import com.goldentour.jee.viewBeans.UserViewBean;

@Service(value = "userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

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

		User user= userDao.findByUsernameAndPassword(username, password);
		if (user != null) {
			return new UserViewBean().getAuthenticate(user);
		}
		else {
			throw new AuthenticationException("User not found");
		}
	}

	@Override
	public UserViewBean find(int idUser) {
		User entity = userDao.find(idUser);
		
		UserViewBean userView = new UserViewBean();
		userView.setUsername(entity.getUsername());
		userView.setRole(entity.getRole().getRolename());
		
		
		return userView;
	}

	@Override
	public void update(UserViewBean currentUser) {
		// TODO Auto-generated method stub
		
	}

}
