package com.goldentour.jee.dao.impl;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.DestinationDao;
import com.goldentour.jee.entities.Destination;

@Repository(value = "destinationDao")
public class DestinationDaoImpl  extends GenericDaoImpl<Destination> implements DestinationDao {

}
