package com.goldentour.jee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goldentour.jee.dao.AccomodationDao;
import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.viewBeans.AccomodationViewBeen;

@Service(value = "accomodationService")
@Transactional(propagation = Propagation.REQUIRED)
public class AccomodationServiceImpl implements AccomodationService{

	@Autowired
	@Qualifier("accomodationDao")
	AccomodationDao accomodationDao;

	@Override
	public List<AccomodationViewBeen> FindAccomodationByDestination(Long id) throws Exception {
		List<Accomodation> accomodationsList;
		List<AccomodationViewBeen> accomodationsUVBList= new ArrayList<AccomodationViewBeen>();


		try {
			System.err.println("stampiamo allegramente");
			accomodationsList = accomodationDao.FindAccomodationsForDestinationId(id);
			System.err.println("Size accomodationsList"+accomodationsList.isEmpty());
			for(Accomodation tmp:accomodationsList) {
				AccomodationViewBeen tmpAVB = new AccomodationViewBeen();
				tmpAVB.setAccomodationType(tmp.getAccomodationType().getName());
				tmpAVB.setAddress(tmp.getAddress());
				tmpAVB.setDestination(tmp.getDestination().getName());
				tmpAVB.setId(tmp.getId());
				tmpAVB.setName(tmp.getName());
				tmpAVB.setPrice(tmp.getPrice());
				tmpAVB.setTelephone(tmp.getTelephone());
				accomodationsUVBList.add(tmpAVB);
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			throw new Exception(e+" errore nella ricerca degli alberghi");

		}
		return accomodationsUVBList;
	}

}
