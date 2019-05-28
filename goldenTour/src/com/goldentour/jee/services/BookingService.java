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

	public Booking createNewBooking(BookingViewBean currentBooking) throws ParseException;
	
	public List<Booking> loadAllBooking() throws Exception;

	public List<BookingViewBean> findAllBooking(long idUser);

	BookingViewBean find(long idBooking);
	
	Booking update(BookingViewBean currentBooking) throws ParseException;

}
