package com.goldentour.jee.services;

import java.util.Date;
import java.util.List;

import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.Transport;
import com.goldentour.jee.entities.User;

public interface BookingService {

	public Booking createNewBooking(Long idBooking, String description, int personNumber, Date startDate, Date endDate,
			int price, User idUser, Transport idTransport, Destination idDestination, Accomodation idAccomodation,
			User idTourOperator) throws Exception;
	
	public Booking find(int idBooking);


	public List<Booking> loadAllBooking() throws Exception;

	public List<Booking> findAllBooking(int idUser);

	public void update(Booking currentBooking);

	public void saveBooking(Booking booking);

}
