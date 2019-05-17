package com.goldentour.jee.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.goldentour.jee.utils.JpaUtils;

public class MasterDao {
	
	public Object findById(Class clazz, Serializable id) {
		
		EntityManager em = JpaUtils.getInstance().getEntityManager();
		Object obj = em.find(clazz, id);
		
		em.close();
		
		return obj;
	}

}
