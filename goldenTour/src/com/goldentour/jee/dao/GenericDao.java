package com.goldentour.jee.dao;

import java.io.Serializable;

public interface GenericDao<t> {
	
	public Object findById(Class clazz, Serializable id);

}
