package com.goldentour.jee.services;

import java.util.List;

import com.goldentour.jee.entities.Transport;

public interface TransportService {


	public List<Transport> FindTransportByDestination(int id);
	

}
