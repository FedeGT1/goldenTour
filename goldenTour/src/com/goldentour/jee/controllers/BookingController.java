package com.goldentour.jee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.User;
import com.goldentour.jee.services.AccomodationService;
import com.goldentour.jee.services.BookingService;
import com.goldentour.jee.services.DestinationService;
import com.goldentour.jee.services.UserService;

@RestController
@RequestMapping("/booking")
public class BookingController {


	@Autowired
	private BookingService bookingService;
	@Autowired
	private DestinationService destinationServices;
	/*@Autowired
	private AccomodationService accomodationServices;*/


	//--------------Ritorna prenotazioni dell'utente selezionato----------------------------------------
	@RequestMapping(value = "/user/{idUser}", method = RequestMethod.GET)
	public ResponseEntity<Booking> getBooking(@PathVariable("idUser") int idUser) {
		Booking booking;

		try {		
			booking = bookingService.find(idUser);
			if (booking == null) {
				//stampare messaggio in front "utente non ha prenotazioni"
				return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Booking>(booking, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//--------------Ritorna tutte le prenotazioni del tour operator selezionato--------------------------
	@RequestMapping(value = "/to/{idUser}", method = RequestMethod.GET)
	public ResponseEntity<List<Booking>> ListAllTOBooking(@PathVariable("idUser") int idUser) {
		List<Booking> bookingList;

		try {		
			bookingList = bookingService.findAllBooking(idUser);
			if (bookingList == null) {
				//stampare messaggio in front "tour operator non ha prenotazioni"
				return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo chiamato all'invocazione della creazione di una prenotazione
	 * 
	 * @return la lista delle localit� disponibili per le prenotazioni
	 */
	@RequestMapping(value = "/to/create/", method = RequestMethod.GET)
	public ResponseEntity<List<Destination>> AllAvaibleDestination() {
		List<Destination> destinations;
		try {
			destinations = destinationServices.FindAllDestination();
			if (destinations.isEmpty()) {
				return new ResponseEntity<List<Destination>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Destination>>(destinations, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Destination>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}// end CreateBooking

	/**
	 * Funzione che selezionata una localit� torna le strutture disponibili.
	 * 
	 * @param id della localit� cercata
	 * @return Lista di strutture disponibili per la prenotazione in una determinata
	 *         localit�
	 */
	/*@RequestMapping(value = "/to/create/Accomodation/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<Accomodation>> SearchAccomodationByDestination(@PathVariable("id") int id) {

		List<Accomodation> accomodations;

		try {
			accomodations = accomodationServices.FindAccomodationByDestination(id);
			if (accomodations.isEmpty()) {
				return new ResponseEntity<List<Accomodation>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Accomodation>>(accomodations, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Accomodation>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}*/// end SearchAccomodationByDestination

	@RequestMapping(value = "/to/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Booking> updateBooking(@PathVariable("id") int id, @RequestBody Booking booking) {
		Booking currentBooking;
		try {
			currentBooking = bookingService.find(id);
			if (currentBooking == null) {
				System.out.println("Booking with id" + id + "not found!");
				return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
			}

			currentBooking.setDescription(booking.getDescription());
			currentBooking.setStartDate(booking.getStartDate());
			currentBooking.setEndDate(booking.getEndDate());
			currentBooking.setAccomodation(booking.getAccomodation());
			currentBooking.setDestination(booking.getDestination());
			currentBooking.setTransport(booking.getTransport());
			currentBooking.setUser(booking.getUser());
			currentBooking.setPersonNumber(booking.getPersonNumber());

			bookingService.update(currentBooking);
			return new ResponseEntity<Booking>(currentBooking, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	// Creazione nuova prenotazione
	@RequestMapping(value = "/to/newBooking/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBooking(@RequestBody Booking booking, UriComponentsBuilder ucBuilder) {
		bookingService.saveBooking(booking);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/booking/id").buildAndExpand(booking.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}


}
