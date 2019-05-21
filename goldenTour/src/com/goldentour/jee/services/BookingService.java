package com.goldentour.jee.services;

import com.goldentour.jee.entities.Booking;

public interface BookingService {

	Booking find(int id);

	void update(Booking currentBooking);

	void saveBooking(Booking booking);

}
