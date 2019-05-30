package com.goldentour.jee.dao;

import com.goldentour.jee.entities.Accomodation;

import java.util.List;

public interface AccomodationDao extends GenericDao<Accomodation> {

    List<Accomodation> FindAccomodationsForDestinationId(Long id);

}
