package com.goldentour.jee.dao;

import java.util.List;

import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.User;


public interface BookingDao extends GenericDao<Booking> {

	Booking findBookingById(Long idBooking);

	List<Booking> findBookingByUser(int idUser);

	List<Booking> findAllBookingByTourOperator(int idTourOperator);

	List<Booking> findAllBookingByDestination(Destination idDestination);

	Booking findBookingByUser(User idUser);


}