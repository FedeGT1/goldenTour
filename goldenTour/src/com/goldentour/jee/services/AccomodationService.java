package com.goldentour.jee.services;

import com.goldentour.jee.exception.AccomodationException;
import com.goldentour.jee.viewBeans.AccomodationViewBeen;

import java.util.List;

public interface AccomodationService {


	List<AccomodationViewBeen> findAccomodationByDestination(Long id) throws AccomodationException;

}
