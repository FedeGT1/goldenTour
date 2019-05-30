package com.goldentour.jee.services;

import com.goldentour.jee.dao.DestinationDao;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.exception.DestinationException;
import com.goldentour.jee.viewBeans.DestinationViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "destinationService")
@Transactional(propagation = Propagation.REQUIRED)
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	@Qualifier("destinationDao")
	DestinationDao destinationDao;

	@Override
	public List<DestinationViewBean> findAllDestination() throws DestinationException {
		List<Destination> destinations = destinationDao.findAll();
		List<DestinationViewBean> destinationsList = new ArrayList<>();
		if (destinations != null) {
			for (Destination tmp : destinations) {
				DestinationViewBean tmpDVB = new DestinationViewBean();
				tmpDVB.setCounty(tmp.getCounty());
				tmpDVB.setName(tmp.getName());
				destinationsList.add(tmpDVB);
			}
			return destinationsList;
		} else {
			throw new DestinationException("Destination not found");
		}
	}

}