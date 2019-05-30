package com.goldentour.jee.services;

import com.goldentour.jee.entities.Transport;

import java.util.List;

public interface TransportService {


	List<Transport> FindTransportByDestination(int id);


}
