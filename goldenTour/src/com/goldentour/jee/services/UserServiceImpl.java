package com.goldentour.jee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goldentour.jee.dao.UserDao;
import com.goldentour.jee.entities.Role;
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
	public UserViewBean authorize(String username, String password) throws Exception {

		User user = userDao.findByUsernameAndPassword(username, password);
		if (user != null) {
			return new UserViewBean().getAuthenticate(user);
		}
		else {
			throw new AuthenticationException("User not found");
		}
	}

	@Override
	public UserViewBean find(long idUser) {
		User entity = userDao.find(idUser);
		
		UserViewBean userView = new UserViewBean();
		userView.setId(entity.getIduser());
		userView.setUsername(entity.getUsername());
		userView.setRole(entity.getRole().getRolename());
		
		userView.setName(entity.getName());
		userView.setLastname(entity.getLastname());
		userView.setEmail(entity.getEmail());
		userView.setAddress(entity.getAddress());
		userView.setCity(entity.getCity());
		userView.setBirthplace(entity.getBirthplace());
		
		return userView;
	}

	@Override
	public User update(UserViewBean currentUser) {	
		User entity = userDao.find(currentUser.getId());
		
		entity.setName(currentUser.getName());
		entity.setLastname(currentUser.getLastname());
		entity.setAddress(currentUser.getAddress());
		entity.setEmail(currentUser.getEmail());
		entity.setCity(currentUser.getCity());
		entity.setBirthplace(currentUser.getBirthplace());

		return userDao.update(entity);	
	}

	 @Override
	    public List<UserViewBean> returnClients(String name, String lastname) throws AuthenticationException {
	        List<User> users = userDao.findByNameAndLastname(name, lastname);
	        List<UserViewBean> usersVB=new ArrayList();
			UserViewBean userView = new UserViewBean();
	        if (users.size() > 0) {
	            for(int i = 0; i != users.size(); i++){
	            	usersVB.add(userView);
	                usersVB.get(i).fromEntity(users.get(i));
	            }
	            return usersVB;
	        } else {
	            throw new AuthenticationException("Clients not found");
	        }
	    }
	 
	    @Override
	    public User register(UserViewBean currentUser){
	    	User entity = new User();
	    	Role role = new Role();
	    	
	    	if (currentUser.getRole().equals("TourOperator")) {
	    		role.setIdRole(1l);
	    		role.setRolename("TourOperator");
	    	} else if (currentUser.getRole().equals("User")){
	    		role.setIdRole(2l);
	    		role.setRolename("User");
	    	}
	    	
	    	entity.setUsername(currentUser.getUsername());
	    	entity.setPassword(currentUser.getPassword());

	    	entity.setName(currentUser.getName());
			entity.setLastname(currentUser.getLastname());
			entity.setAddress(currentUser.getAddress());
			entity.setCity(currentUser.getCity());
			entity.setBirthday(currentUser.getBirthday());
			entity.setBirthplace(currentUser.getBirthplace());
			entity.setCap(currentUser.getCap());
			entity.setEmail(currentUser.getEmail());
			entity.setRole(role);
	    	
	    	return userDao.create(entity);

	    }
}
