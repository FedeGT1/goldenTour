package com.goldentour.jee.services;

import com.goldentour.jee.exception.DestinationException;
import com.goldentour.jee.viewBeans.DestinationViewBean;

import java.util.List;

public interface DestinationService {

    List<DestinationViewBean> findAllDestination() throws DestinationException;

}
