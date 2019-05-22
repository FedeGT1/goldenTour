package com.goldentour.jee.services;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

public class BookingServiceImpl implements BookingService {

	@Autowired
	@Qualifier("bookingDao")
	BookingDao bookingDao;

	@Autowired
	@Qualifier("destinationDao")
	DestinationDao destinationDao;

	@Autowired
	@Qualifier("trasportDao")
	TransportDao trasportDao;

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

		Booking b = new Booking();

		try {

			// Recupero idUser
			User user = userDao.find(idUser);
			b.setIdUser(idUser);

			// Recupero idTransport
			Transport transport = trasportDao.find(idTransport);
			b.setIdTransport(idTransport);

			// Recupero idDestinazione
			Destination destination = destinationDao.find(idDestination);
			b.setIdDestination(idDestination);

			// Recupero idAccomodation
			Accomodation accomodation = accomodationDao.find(idAccomodation);
			b.setIdAccomodation(idAccomodation);

			// Recupero idTourOperator
			User tourOperator = userDao.find(idTourOperator);
			b.setIdTourOperator(idTourOperator);

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
	}

	// @LogExecutionTime
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<Booking> loadAllBooking() throws Exception {
		return bookingDao.findAll();
	}

	@Override
	public Booking find(int idBooking) {
		return bookingDao.find(idBooking);
	}

	@Override
	public List<Booking> findAllBooking(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Booking currentBooking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	}


}
