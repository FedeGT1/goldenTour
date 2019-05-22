package com.goldentour.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.BookingDao;
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
	public List<Booking> findAllBookingByTourOperator(User idTourOperator) {
		List<Booking> b = null;
		try {
			Query q = em.createQuery(
					"SELECT a.idBooking, a.description, a.personNumber, a.startDate, a.endDate, a.price, a.idUser FROM Booking a WHERE a.idTourOperator = :idTourOperator");

			q.setParameter("idTourOperator", idTourOperator);
			b = q.getResultList();

		} finally {
			em.close();
		}
		return b;
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

}