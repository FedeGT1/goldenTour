package com.goldentour.jee.services;

import java.util.List;

import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.viewBeans.DestinationViewBean;

public interface DestinationService {

	List<DestinationViewBean> FindAllDestination();

}
