package com.goldentour.jee.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldentour.jee.dao.BookingDao;
import com.goldentour.jee.entities.Booking;

public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Override
	public Booking find(int id) {
		//TODO
		return null;
	}

	@Override
	@Transactional
	public void update(Booking booking) {
		//TODO

	}

	@Override
	@Transactional
	public void saveBooking(Booking booking) {
	//TODO
	}

}