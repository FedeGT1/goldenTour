package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.BookingDao;
import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "bookingDao")
public class BookingDaoImpl extends GenericDaoImpl<Booking> implements BookingDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Booking findBookingByUser(User idUser) {
		Booking b;
		try {
			b = em.find(Booking.class, idUser);
		} finally {
			em.close();
		}
		return b;
	}

	@Override
	public List<Booking> findAllBookingByTourOperator(long id) {
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
		List<Booking> b;
		try {
			Query q = em.createQuery(
					"SELECT a.id, a.description, a.personNumber, a.startDate, a.endDate, a.price, a.user FROM Booking a WHERE a.destination = :idDestination");

			q.setParameter("idDestination", idDestination);
			b = q.getResultList();

		} finally {
			em.close();
		}
		return b;
	}

	@Override
	public List<Booking> findBookingByUser(long idUser) {
		List<Booking> bookings;
		try {
			Query q = em.createQuery("SELECT b from Booking b WHERE b.user = :user_id", Booking.class);
			q.setParameter("user_id", em.getReference(User.class, idUser));
			bookings = q.getResultList();
			if (bookings.size() != 0) {
				return bookings;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
}