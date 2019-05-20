package com.goldentour.jee.dao;

import java.io.Serializable;

public interface GenericDao<T> {
	
	public Object findById(Serializable id);

}
