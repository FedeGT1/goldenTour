package com.goldentour.jee.dao;

import javax.persistence.EntityManager;

public interface GenericDao<T>  {
	
	T create(T t);
	
	void delete(Object id);
	
	T find(Object id);
	
	T update(T t);
	
	EntityManager getEntityManager();
	
}
