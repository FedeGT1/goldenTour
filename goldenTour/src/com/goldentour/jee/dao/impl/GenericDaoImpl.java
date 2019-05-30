package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.GenericDao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


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
    public EntityManager getEntityManager() {
        return em;
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
        return this.em.find(type, id);

    }

    @Override
    public T update(final T t) {
        return this.em.merge(t);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) em.createQuery("SELECT t FROM " + type.getSimpleName() + " t").getResultList();
    }

}