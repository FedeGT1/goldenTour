package com.goldentour.jee.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		type = (Class<T>) pt.getActualTypeArguments()[0];
		
	}

	@Override
	public Object findById(Serializable id) {
		Object obj = em.find(type, id);
		return obj;
	}

	@Override
	public T create(final T t) {
this.em.persist(t);
return t;
	}

	@Override
	public void delete(final Object id) {
 this.em.remove(this.em.getReference(type, id));		
	}

	@Override
	public T find(final Object id) {
		return this.em.find(type,  id);
		
	}

	@Override
	public T update(final T t) {
		return this.em.merge(t);
	}

	@Override
	public List<T> findAll() {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

				CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);

				Root<T> root = criteriaQuery.from(type);

				criteriaQuery.select(root);

				return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
