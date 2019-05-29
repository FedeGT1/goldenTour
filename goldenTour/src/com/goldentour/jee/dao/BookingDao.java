package com.goldentour.jee.dao;

import java.util.List;

import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.User;


public interface BookingDao extends GenericDao<Booking> {

	List<Booking> findBookingByUser(long idUser);

	List<Booking> findAllBookingByTourOperator(long idTourOperator);

	List<Booking> findAllBookingByDestination(Destination idDestination);
	
	Booking findBookingByUser(User idUser);

}