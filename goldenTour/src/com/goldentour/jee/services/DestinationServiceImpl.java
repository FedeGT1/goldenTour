package com.goldentour.jee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goldentour.jee.dao.DestinationDao;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.viewBeans.DestinationViewBean;

@Service(value = "destinationService")
@Transactional(propagation = Propagation.REQUIRED)
public class DestinationServiceImpl implements DestinationService{

	@Autowired
	@Qualifier("destinationDao")
	DestinationDao destinationDao;
	
	@Override
	public List<DestinationViewBean> FindAllDestination() {
		List<Destination> destinations = (List<Destination>) destinationDao.findAll();
		List<DestinationViewBean> destinationsList = new ArrayList<DestinationViewBean>();
		for(Destination tmp:destinations) {
			DestinationViewBean tmpDVB = new DestinationViewBean();
			tmpDVB.setCounty(tmp.getCounty());
			tmpDVB.setName(tmp.getName());
			destinationsList.add(tmpDVB);
		}
		return destinationsList;
	}

}
