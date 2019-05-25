package com.goldentour.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.BookingDao;
import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.User;

@Repository(value = "bookingDao")
public class BookingDaoImpl extends GenericDaoImpl<Booking> implements BookingDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Booking findBookingById(Long idBooking) {
		Booking b = null;
		try {
			b = em.find(Booking.class, idBooking);
		} finally {
			em.close();
		}
		return b;
	}

	@Override
	public Booking findBookingByUser(User idUser) {
		Booking b = null;
		try {
			b = em.find(Booking.class, idUser);
		} finally {
			em.close();
		}
		return b;
	}

	@Override
	public List<Booking> findAllBookingByTourOperator(int id) {
		List<Booking> bookings;
		try {
			Query q = em.createQuery("SELECT b from Booking b WHERE b.tourOperator = :tourOperator_id", Booking.class);
			q.setParameter("tourOperator_id", em.getReference(User.class, id));
			bookings = q.getResultList();
			if (bookings.size() != 0)
				return bookings;
			else
				return null;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Booking> findAllBookingByDestination(Destination idDestination) {
		List<Booking> b = null;
		try {
			Query q = em.createQuery(
					"SELECT a.idBooking, a.description, a.personNumber, a.startDate, a.endDate, a.price, a.idUser FROM Booking a WHERE a.idDestination = :idDestination");

			q.setParameter("idDestination", idDestination);
			b = q.getResultList();

		} finally {
			em.close();
		}
		return b;
	}

	@Override
	public List<Booking> findBookingByUser(int idUser) {
		List<Booking> bookings;
		try {
			Query q = em.createQuery("SELECT b from Booking b WHERE b.user = :user_id", Booking.class);
			q.setParameter("user_id", em.getReference(User.class, idUser));
			bookings = q.getResultList();
			if (bookings.size() != 0)
				return bookings;
			else
				return null;
		} finally {
			em.close();
		}
	}

}