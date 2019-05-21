package com.goldentour.jee.services;

import java.util.List;

import com.goldentour.jee.entities.Accomodation;

public interface AccomodationService {


	List<Accomodation> FindAccomodationByDestination(int id);

}
