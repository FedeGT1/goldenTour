package com.goldentour.jee.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goldentour.jee.entities.Destination;

@Service(value = "destinationService")
@Transactional(propagation = Propagation.REQUIRED)
public class DestinationServiceImpl implements DestinationService{

	@Override
	public List<Destination> FindAllDestination() {
		return null;
	}

}
