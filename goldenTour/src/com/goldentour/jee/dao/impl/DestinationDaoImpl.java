package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.DestinationDao;
import com.goldentour.jee.entities.Destination;
import org.springframework.stereotype.Repository;

@Repository(value = "destinationDao")
public class DestinationDaoImpl extends GenericDaoImpl<Destination> implements DestinationDao {

}
