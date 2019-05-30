package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.RoleDao;
import com.goldentour.jee.entities.Role;
import org.springframework.stereotype.Repository;

@Repository(value = "roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {


}
