package com.goldentour.jee.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDao<T>  {
	
	T create(T t);
	
	void delete(Object id);
	
	T find(Object id);
	
	T update(T t);
	
	List<T> findAll();
	
	EntityManager getEntityManager();
	
	public Object findById(Serializable id);

}
