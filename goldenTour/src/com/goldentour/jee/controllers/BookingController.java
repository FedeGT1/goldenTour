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
	private UserService userService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private DestinationService destinationServices;
	@Autowired
	private AccomodationService accomodationServices;

	/**
	 * Metodo chiamato all'invocazione della creazione di una prenotazione
	 * 
	 * @return la lista delle località disponibili per le prenotazioni
	 */
	@RequestMapping(value = "/create/", method = RequestMethod.GET)
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
	 * Funzione che selezionata una località torna le strutture disponibili.
	 * 
	 * @param id della località cercata
	 * @return Lista di strutture disponibili per la prenotazione in una determinata
	 *         località
	 */
	@RequestMapping(value = "/create/Accomodation/{id}", method = RequestMethod.POST)
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

	}// end SearchAccomodationByDestination

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
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
			currentBooking.setIdAccomodation(booking.getIdAccomodation());
			currentBooking.setIdDestination(booking.getIdDestination());
			currentBooking.setIdTransport(booking.getIdTransport());
			currentBooking.setIdUser(booking.getIdUser());
			currentBooking.setPersonNumber(booking.getPersonNumber());

			bookingService.update(currentBooking);
			return new ResponseEntity<Booking>(currentBooking, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Ricerca di un utente se è già nel database
	@RequestMapping(value = "/user/{FiscalCode}/", method = RequestMethod.POST)
	public ResponseEntity<User> SearchUserByFiscalCode(@PathVariable("FiscalCode") String fiscalCode) {
		User user;
		try {
			user = userService.findByFiscalCode(fiscalCode);
			if (user == null)
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Creazione nuova prenotazione
	@RequestMapping(value = "/newBooking/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBooking(@RequestBody Booking booking, UriComponentsBuilder ucBuilder) {
		bookingService.saveBooking(booking);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/booking/id").buildAndExpand(booking.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	// Creazione un nuovo utente
	@RequestMapping(value = "/newUser/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/id").buildAndExpand(user.getIduser()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	
	/**
	 * Appare quando si è conclusa la prenotazione e si è nel carrello per la conferma della
	 * prenotazione
	 * @param id
	 * @return La prenotazione appena effettuata
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET /*
																 * ,produces = { MediaType.APPLICATION_JSON_VALUE,
																 * MediaType.APPLICATION_XML_VALUE }
																 */)
	public ResponseEntity<Booking> getBooking(@PathVariable("id") int id) {
		Booking booking;
		try {
			booking = bookingService.find(id);
			if (booking == null)
				return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Booking>(booking, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
