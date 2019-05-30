package com.goldentour.jee.services;

import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.exception.BookingException;
import com.goldentour.jee.viewBeans.BookingViewBean;

import java.text.ParseException;
import java.util.List;

public interface BookingService {

	Booking createNewBooking(BookingViewBean currentBooking) throws ParseException;

	List<Booking> loadAllBooking() throws Exception;

	List<BookingViewBean> findAllBooking(int idUser) throws BookingException;

	BookingViewBean find(long idBooking) throws BookingException;

	List<BookingViewBean> findAllBookingForTourOperator(int idTourOperator) throws BookingException;

	Booking update(BookingViewBean currentBooking) throws ParseException, BookingException;

}
