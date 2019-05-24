package com.goldentour.jee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.goldentour.jee.services.AccomodationServiceImpl;
import com.goldentour.jee.services.BookingService;
import com.goldentour.jee.services.DestinationService;
import com.goldentour.jee.services.UserService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private DestinationService destinationService;
	
	@Autowired
	private AccomodationService accomodationService;

	
	//--------------Ritorna prenotazioni dell'utente selezionato---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/user/{idUser}", method = RequestMethod.GET)
	public ResponseEntity<List<Booking>> getAllBooking(@PathVariable("idUser") int idUser) {
		List<Booking> booking;

		try {		
			booking = bookingService.findAllBooking(idUser);
			if (booking.isEmpty()) {
				//stampare messaggio in front "utente non ha prenotazioni"
				return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Booking>>(booking, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//--------------Ritorna tutte le prenotazioni del tour operator selezionato-----------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/to/{idTourOperator}", method = RequestMethod.GET)
	public ResponseEntity<List<Booking>> ListAllBooking(@PathVariable("idTourOperator") int idTourOperator) {
		List<Booking> booking;

		try {		
			booking = bookingService.findAllBooking(idTourOperator);
			if (booking.isEmpty()) {
				//stampare messaggio in front "tour operator non ha prenotazioni"
				return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Booking>>(booking, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//--------------Ritorna tutte le destinazioni----------------------------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/to/destinations/", method = RequestMethod.GET)
	public ResponseEntity<List<Destination>> AllAvaibleDestination() {
		List<Destination> destinations;
		try {
			destinations = destinationService.findAllDestination();
			if (destinations.isEmpty()) {
				return new ResponseEntity<List<Destination>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Destination>>(destinations, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Destination>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	//--------------Ritorna tutte le accomodation di una destionation selezionata----------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/to/destination/{idDestiantion}/accomodations", method = RequestMethod.POST)
	public ResponseEntity<List<Accomodation>> AllAccomodationByDestination(@PathVariable("idDestination") int idDestination) {

		List<Accomodation> accomodations;

		try {
			accomodations = accomodationService.findAccomodationByDestination(idDestination);
			if (accomodations.isEmpty()) {
				return new ResponseEntity<List<Accomodation>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Accomodation>>(accomodations, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Accomodation>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	//----------------------------Modifica prenotazione di un utente----------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/to/update/{idUser}", method = RequestMethod.PUT)
	public ResponseEntity<Booking> updateBooking(@PathVariable("idUser") int idUser, @RequestBody Booking booking) {
		Booking currentBooking;
		try {
			currentBooking = bookingService.find(idUser);
			if (currentBooking == null) {
				System.out.println("Booking with id" + idUser + "not found!");
				return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
			}

			currentBooking.setDescription(booking.getDescription());
			currentBooking.setPersonNumber(booking.getPersonNumber());
			currentBooking.setStartDate(booking.getStartDate());
			currentBooking.setEndDate(booking.getEndDate());
			currentBooking.setIdAccomodation(booking.getIdAccomodation());
			currentBooking.setIdDestination(booking.getIdDestination());
			currentBooking.setIdTransport(booking.getIdTransport());
			currentBooking.setUser(booking.getUser());

			bookingService.update(currentBooking);
			return new ResponseEntity<Booking>(currentBooking, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	//----------------------------Creazione nuova prenotazione--------------------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/to/newBooking/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBooking(@RequestBody Booking booking, UriComponentsBuilder ucBuilder) {
		bookingService.saveBooking(booking);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/booking/id").buildAndExpand(booking.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}


}
