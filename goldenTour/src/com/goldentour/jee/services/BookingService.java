package com.goldentour.jee.services;

import java.util.Date;
import java.util.List;

import com.goldentour.jee.entities.Booking;

public interface BookingService {

	public Booking createNewBooking(Long idBooking, String description, int personNumber, Date startDate, Date endDate,
			int price, Long idUser, Long idTransport, Long idDestination, Long idAccomodation,
			Long idTourOperator) throws Exception;
	
	public Booking find(int idBooking);


	public List<Booking> loadAllBooking() throws Exception;

	public List<Booking> findAllBooking(int idUser);

	public void update(Booking currentBooking);

	public void saveBooking(Booking booking);

}
