package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.TransportDao;
import com.goldentour.jee.entities.Transport;
import org.springframework.stereotype.Repository;

@Repository(value = "transportDao")
public class TransportDaoImpl extends GenericDaoImpl<Transport> implements TransportDao {

}
