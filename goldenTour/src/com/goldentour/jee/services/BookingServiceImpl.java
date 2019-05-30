package com.goldentour.jee.services;

import com.goldentour.jee.dao.*;
import com.goldentour.jee.entities.*;
import com.goldentour.jee.exception.BookingException;
import com.goldentour.jee.viewBeans.BookingViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
	public Booking createNewBooking(BookingViewBean bookingViewBean) throws ParseException {

		Booking booking = new Booking();
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");

		User user = new User();
		user.setIduser(bookingViewBean.getUser());

		Accomodation accomodation = new Accomodation();
		accomodation.setId(bookingViewBean.getAccomodation());

		Destination destination = new Destination();
		destination.setId(bookingViewBean.getDestination());

		Transport transport = new Transport();
		transport.setId(bookingViewBean.getTransport());

		User to = new User();
		to.setIduser(bookingViewBean.getTourOperator());


		booking.setDescription(bookingViewBean.getDescription());
		booking.setPersonNumber(bookingViewBean.getPersonNumber());
		booking.setStartDate(data.parse(bookingViewBean.getStartDate()));
		booking.setEndDate(data.parse(bookingViewBean.getEndDate()));
		booking.setPrice(bookingViewBean.getPrice());
		booking.setUser(user);
		booking.setAccomodation(accomodation);
		booking.setDestination(destination);
		booking.setTransport(transport);
		booking.setTourOperator(to);

		return bookingDao.create(booking);
	}

	// @LogExecutionTime
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<Booking> loadAllBooking() {
		return bookingDao.findAll();
	}

	@Override
	public BookingViewBean find(long idBooking) throws BookingException {
		Booking booking = bookingDao.find(idBooking);
		if (booking != null) {
			BookingViewBean bookingvb = new BookingViewBean();
			bookingvb.setAccomodation(booking.getAccomodation().getId());
			bookingvb.setDescription(booking.getDescription());
			bookingvb.setDestination(booking.getDestination().getId());
			bookingvb.setEndDate("" + booking.getEndDate());
			bookingvb.setStartDate("" + booking.getStartDate());
			bookingvb.setId(booking.getId());
			bookingvb.setPersonNumber(booking.getPersonNumber());
			bookingvb.setPrice(booking.getPrice());
			bookingvb.setTourOperator(booking.getTourOperator().getIduser());
			bookingvb.setTransport(booking.getTransport().getId());
			bookingvb.setUser(booking.getUser().getIduser());

			return bookingvb;
		} else {
			throw new BookingException("Booking not found");
		}
	}

	@Override
	public Booking update(BookingViewBean currentBooking) throws ParseException, BookingException {
		Booking entity = bookingDao.find(currentBooking.getId());
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
		if (entity != null) {
			entity.setEndDate(data.parse(currentBooking.getEndDate()));
			entity.setStartDate(data.parse(currentBooking.getStartDate()));
			entity.setPersonNumber(currentBooking.getPersonNumber());
			entity.setPrice(currentBooking.getPrice());

			return bookingDao.update(entity);
		} else {
			throw new BookingException("Booking not modified");
		}

	}

	@Override
	public List<BookingViewBean> findAllBooking(int idUser) throws BookingException {
		List<Booking> booking = bookingDao.findBookingByUser(idUser);
		List<BookingViewBean> bookingViewBeanList = new ArrayList<>();
		if (booking.size() > 0) {
			for (Booking tmp : booking) {
				BookingViewBean bookingvb = new BookingViewBean();
				bookingvb.setAccomodation(tmp.getAccomodation().getId());
				bookingvb.setDescription(tmp.getDescription());
				bookingvb.setDestination(tmp.getDestination().getId());
				bookingvb.setEndDate("" + tmp.getEndDate());
				bookingvb.setStartDate("" + tmp.getStartDate());
				bookingvb.setId(tmp.getId());
				bookingvb.setPersonNumber(tmp.getPersonNumber());
				bookingvb.setPrice(tmp.getPrice());
				bookingvb.setTourOperator(tmp.getTourOperator().getIduser());
				bookingvb.setTransport(tmp.getTransport().getId());
				bookingvb.setUser(tmp.getUser().getIduser());
				bookingViewBeanList.add(bookingvb);
			}
			return bookingViewBeanList;
		} else {
			throw new BookingException("Booking not found");

		}
	}

	@Override
	public List<BookingViewBean> findAllBookingForTourOperator(int idTourOperator) throws BookingException {
		List<Booking> booking = bookingDao.findAllBookingByTourOperator(idTourOperator);
		List<BookingViewBean> bookingViewBeanList = new ArrayList<>();
		if (booking != null) {
			for (Booking tmp : booking) {
				BookingViewBean bookingvb = new BookingViewBean();
				bookingvb.setAccomodation(tmp.getAccomodation().getId());
				bookingvb.setDescription(tmp.getDescription());
				bookingvb.setDestination(tmp.getDestination().getId());
				bookingvb.setEndDate("" + tmp.getEndDate());
				bookingvb.setStartDate("" + tmp.getStartDate());
				bookingvb.setId(tmp.getId());
				bookingvb.setPersonNumber(tmp.getPersonNumber());
				bookingvb.setPrice(tmp.getPrice());
				bookingvb.setTourOperator(tmp.getTourOperator().getIduser());
				bookingvb.setTransport(tmp.getTransport().getId());
				bookingvb.setUser(tmp.getUser().getIduser());
				bookingViewBeanList.add(bookingvb);
			}
			return bookingViewBeanList;
		} else {
			throw new BookingException("Booking not found");
		}

	}

}
