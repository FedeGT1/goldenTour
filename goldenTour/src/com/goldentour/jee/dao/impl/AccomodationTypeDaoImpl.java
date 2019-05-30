package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.AccomodationTypeDao;
import com.goldentour.jee.entities.AccomodationType;
import org.springframework.stereotype.Repository;

@Repository(value = "accomodationTypeDao")
public class AccomodationTypeDaoImpl extends GenericDaoImpl<AccomodationType> implements AccomodationTypeDao {

}
