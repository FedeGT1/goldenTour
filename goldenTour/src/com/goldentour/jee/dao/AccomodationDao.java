package com.goldentour.jee.dao;

import java.util.List;

import com.goldentour.jee.entities.Accomodation;

public interface AccomodationDao extends GenericDao<Accomodation>  {
	
	public List<Accomodation> FindAccomodationsForDestinationId(Long id);

}
