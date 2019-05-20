package com.goldentour.jee.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.goldentour.jee.dao.GenericDao;
import com.goldentour.jee.utils.JpaUtils;

public class GenericDaoImpl<T> implements GenericDao<T> {
	
	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> type;
	
	@PostConstruct
	public void init() {
		
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
		
	}

	@Override
	public Object findById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/*public Object findById(Class clazz, Serializable id) {
		
		EntityManager em = JpaUtils.getInstance().getEntityManager();
		Object obj = em.find(clazz, id);
		
		em.close();
		
		return obj;
	}*/

}
