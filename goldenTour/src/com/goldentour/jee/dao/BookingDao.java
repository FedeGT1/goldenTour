package com.goldentour.jee.dao;

import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.User;

import java.util.List;


public interface BookingDao extends GenericDao<Booking> {

	List<Booking> findBookingByUser(long idUser);

	List<Booking> findAllBookingByTourOperator(long idTourOperator);

	List<Booking> findAllBookingByDestination(Destination idDestination);

    Booking findBookingByUser(User idUser);

}