package com.goldentour.jee.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.goldentour.jee.entities.Role;
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
	public List<Booking> loadAllBooking() throws Exception {
		return bookingDao.findAll();
	}

	@Override
	public BookingViewBean find(long idBooking) {
		Booking booking = bookingDao.find(idBooking);
		
		BookingViewBean bookingvb = new BookingViewBean();
		bookingvb.setAccomodation(booking.getAccomodation().getId());
		bookingvb.setDescription(booking.getDescription());
		bookingvb.setDestination(booking.getDestination().getId());
		bookingvb.setEndDate(""+booking.getEndDate());
		bookingvb.setStartDate(""+booking.getStartDate());
		bookingvb.setId(booking.getId());
		bookingvb.setPersonNumber(booking.getPersonNumber());
		bookingvb.setPrice(booking.getPrice());
		bookingvb.setTourOperator(booking.getTourOperator().getIduser());
		bookingvb.setTransport(booking.getTransport().getId());
		bookingvb.setUser(booking.getUser().getIduser());
		
		return bookingvb;
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
	public List<BookingViewBean> findAllBooking(long idUser) {
		List<Booking> booking = null;
		User user = userDao.find(idUser);
		
		if(user.getRole().getIdRole().equals(2)) booking = bookingDao.findBookingByUser(idUser);
		else booking = bookingDao.findAllBookingByTourOperator(idUser);
		
		List<BookingViewBean> bookingViewBeanList = new ArrayList<BookingViewBean>();
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
	}
}
