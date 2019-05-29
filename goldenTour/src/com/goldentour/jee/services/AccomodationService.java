package com.goldentour.jee.services;

import java.util.List;

import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.exception.AccomodationException;
import com.goldentour.jee.viewBeans.AccomodationViewBeen;

public interface AccomodationService {


	List<AccomodationViewBeen> findAccomodationByDestination(Long id) throws AccomodationException;

}
