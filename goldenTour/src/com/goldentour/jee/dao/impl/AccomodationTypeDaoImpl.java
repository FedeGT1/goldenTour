package com.goldentour.jee.dao.impl;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.AccomodationTypeDao;
import com.goldentour.jee.entities.AccomodationType;

@Repository(value = "accomodationTypeDao")
public class AccomodationTypeDaoImpl  extends GenericDaoImpl<AccomodationType> implements AccomodationTypeDao  {

}
