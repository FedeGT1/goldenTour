package com.goldentour.jee.dao.impl;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.AccomodationDao;
import com.goldentour.jee.entities.Accomodation;

@Repository(value = "accomodationDao")
public class AccomodationDaoImpl extends GenericDaoImpl<Accomodation> implements AccomodationDao{

}
