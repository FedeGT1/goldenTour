package com.goldentour.jee.dao.impl;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.RoleDao;
import com.goldentour.jee.entities.Role;

@Repository(value = "roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao{

	

}
