package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.UserDao;
import com.goldentour.jee.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		List<User> users;

		try {

			Query q = em.createQuery("SELECT u from User u WHERE u.username=:username AND u.password=:password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			users = q.getResultList();
			return users.size() > 0 ? users.get(0) : null;
		} finally {
			em.close();
		}
	}

	@Override
	public List<User> findByNameAndLastname(String name, String lastname) {
        List<User> users;
        try {
            Query q = em.createQuery("SELECT u from User u WHERE u.name=:name AND u.lastname=:lastname");
            q.setParameter("name", name);
            q.setParameter("lastname", lastname);
            users = q.getResultList();
            return users;
        } finally {
            em.close();
        }
    }

}
