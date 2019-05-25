package com.goldentour.jee.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.goldentour.jee.dao.AccomodationDao;
import com.goldentour.jee.dao.BookingDao;
import com.goldentour.jee.dao.DestinationDao;
import com.goldentour.jee.dao.TransportDao;
import com.goldentour.jee.dao.UserDao;
import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.Transport;
import com.goldentour.jee.entities.User;
import com.goldentour.jee.viewBeans.BookingViewBean;

@Service(value = "bookingService")
@Transactional(propagation = Propagation.REQUIRED)
public class BookingServiceImpl implements BookingService {

	@Autowired
	@Qualifier("bookingDao")
	BookingDao bookingDao;

	@Autowired
	@Qualifier("destinationDao")
	DestinationDao destinationDao;

	@Autowired
	@Qualifier("transportDao")
	TransportDao transportDao;

	@Autowired
	@Qualifier("accomodationDao")
	AccomodationDao accomodationDao;

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Booking createNewBooking(Long idBooking, String description, int personNumber, Date startDate, Date endDate,
			int price, User idUser, Transport idTransport, Destination idDestination, Accomodation idAccomodation,
			User idTourOperator) throws Exception {
/*
		Booking b = new Booking();

		try {

			// Recupero idUser
			User user = userDao.find(idUser);
			b.setUser(idUser);

			// Recupero idTransport
			Transport transport = transportDao.find(idTransport);
			b.setIdTransport(idTransport);

			// Recupero idDestinazione
			Destination destination = destinationDao.find(idDestination);
			b.setIdDestination(idDestination);

			// Recupero idAccomodation
			Accomodation accomodation = accomodationDao.find(idAccomodation);
			b.setIdAccomodation(idAccomodation);

			// Recupero idTourOperator
			User tourOperator = userDao.find(idTourOperator);
			b.setTourOperator(idTourOperator);

			b.setDescription(description);
			b.setPersonNumber(personNumber);
			b.setStartDate(startDate);
			b.setEndDate(endDate);
			b.setPrice(price);

			// Creazione prenotazione
			bookingDao.create(b);

			try {
				userDao.update(user);
			} catch (Exception e) {
				throw new Exception("Utente aggiornato ma prenotazione non effettuata.");
			}

		} catch (Exception ex) {
			if (true)
				throw ex;
		}

		return b;
		*/
		return null;
	}

	// @LogExecutionTime
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<Booking> loadAllBooking() throws Exception {
		return bookingDao.findAll();
	}

	@Override
	public BookingViewBean find(long idBooking) {
		Booking booking = bookingDao.find(idBooking);
		
		BookingViewBean bookingvb = new BookingViewBean();
		bookingvb.setAccomodation(booking.getAccomodation().getName());
		bookingvb.setDescription(booking.getDescription());
		bookingvb.setDestination(booking.getDestination().getName());
		bookingvb.setEndDate(""+booking.getEndDate());
		bookingvb.setStartDate(""+booking.getStartDate());
		bookingvb.setId(booking.getId());
		bookingvb.setPersonNumber(booking.getPersonNumber());
		bookingvb.setPrice(booking.getPrice());
		bookingvb.setTourOperator(booking.getTourOperator().getName());
		bookingvb.setTransport(booking.getTransport().getName());
		bookingvb.setUser(booking.getUser().getName());
		
		return bookingvb;
	}

	@Override
	public List<Booking> findAllBooking(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking update(BookingViewBean currentBooking) throws ParseException {
		Booking entity = bookingDao.find(currentBooking.getId());
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
		
		entity.setEndDate(data.parse(currentBooking.getEndDate()));
		entity.setStartDate(data.parse(currentBooking.getStartDate()));
		entity.setPersonNumber(currentBooking.getPersonNumber());
		entity.setPrice(currentBooking.getPrice());
		
		return bookingDao.update(entity);
		
	}

	@Override
	public void saveBooking(BookingViewBean currentBooking) {
		/*Booking entity = null;		
		
		entity.setUser(user);
		
		bookingDao.create(currentBooking);*/

	}
}
