package com.goldentour.jee.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.GenericDao;

@Repository
public class GenericDaoImpl<T> implements GenericDao<T> {
	
	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> type;
	
	//@PostConstruct
	public void init() {
		
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
		
	}

	@Override
	public Object findById(Serializable id) {
		Object obj = em.find(type, id);
		return obj;
	}

}
