package com.goldentour.jee.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.Transport;
import com.goldentour.jee.entities.User;
import com.goldentour.jee.viewBeans.BookingViewBean;

public interface BookingService {

	public Booking createNewBooking(Long idBooking, String description, int personNumber, Date startDate, Date endDate,
			int price, User idUser, Transport idTransport, Destination idDestination, Accomodation idAccomodation,
			User idTourOperator) throws Exception;
	


	public List<Booking> loadAllBooking() throws Exception;

	public List<BookingViewBean> findAllBooking(int idUser);


	public void saveBooking(BookingViewBean currentBooking);

	BookingViewBean find(long idBooking);



	Booking update(BookingViewBean currentBooking) throws ParseException;



	public List<BookingViewBean> findAllBookingForTourOperator(int idTourOperator);

}
