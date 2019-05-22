package com.goldentour.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.UserDao;
import com.goldentour.jee.entities.User;

@Repository(value = "userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<User> findByUsernameAndPassword(String username, String password) {
		List<User> users;

		try {

			  Query q = em.createQuery("SELECT u from User u WHERE u.username=:username AND u.password=:password");
	            q.setParameter("username", username);
	            q.setParameter("password", password);
	            users = q.getResultList();


		} finally {
			em.close();
		}
		return users;
	}

}
