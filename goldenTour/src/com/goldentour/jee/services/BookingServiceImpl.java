package com.goldentour.jee.services;

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
import com.goldentour.jee.viewBeans.BookingViewBeen;

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
	public BookingViewBeen createNewBooking(Long idBooking, String description, int personNumber, Date startDate, Date endDate,
			int price, Long idUser, Long idTransport, Long idDestination, Long idAccomodation,
			Long idTourOperator) throws Exception {

		Booking b = new Booking();
		BookingViewBeen bookingVB = new BookingViewBeen();

		try {

			// Recupero idUser
			User user = userDao.find(idUser);
			b.setUser(user);
			bookingVB.setUser(user.getLastname()+" "+user.getName());

			// Recupero idTransport
			Transport transport = transportDao.find(idTransport);
			b.setTransport(transport);
			bookingVB.setTransport(transport.getName());

			// Recupero idDestinazione
			Destination destination = destinationDao.find(idDestination);
			b.setDestination(destination);
			bookingVB.setDestination(destination.getName()+" "+destination.getCounty());

			// Recupero idAccomodation
			Accomodation accomodation = accomodationDao.find(idAccomodation);
			b.setAccomodation(accomodation);
			bookingVB.setAccomodation(accomodation.getName());

			// Recupero idTourOperator
			User tourOperator = userDao.find(idTourOperator);
			b.setTourOperator(tourOperator);
			bookingVB.setTourOperator(tourOperator.getLastname()+" "+tourOperator.getName());

			b.setDescription(description);
			bookingVB.setDescription(description);
			b.setPersonNumber(personNumber);
			bookingVB.setPersonNumber(""+personNumber);
			b.setStartDate(startDate);
			bookingVB.setStartDate(""+startDate);
			b.setEndDate(endDate);
			bookingVB.setStartDate(""+endDate);
			b.setPrice(price);
			bookingVB.setPrice(""+price);

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

		return bookingVB;
	}

	// @LogExecutionTime
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<Booking> loadAllBooking() throws Exception {
		return bookingDao.findAll();
	}

	@Override
	public BookingViewBeen find(long idBooking) {
		Booking booking = bookingDao.find(idBooking);
		BookingViewBeen bookingvb = new BookingViewBeen();
		bookingvb.setAccomodation(""+booking.getAccomodation().getName());
		bookingvb.setDescription(booking.getDescription());
		bookingvb.setDestination(booking.getDestination().getName());
		bookingvb.setEndDate(""+booking.getEndDate());
		bookingvb.setStartDate(""+booking.getStartDate());
		bookingvb.setId(""+booking.getId());
		bookingvb.setPersonNumber(""+booking.getPersonNumber());
		bookingvb.setPrice(""+booking.getPrice());
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
	public void update(Booking currentBooking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	}


}
