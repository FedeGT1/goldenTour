package com.goldentour.services;

import java.util.Date;
import java.util.List;

import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.Transport;
import com.goldentour.jee.entities.User;

public interface BookingService {

	public Booking createNewBooking(Long idBooking, String description, int personNumber, Date startDate, Date endDate,
			int price, Long idUser, Long idTransport, Long idDestination, Long idAccomodation,
			Long idTourOperator) throws Exception;

	public List<Booking> loadAllBooking() throws Exception;

}
