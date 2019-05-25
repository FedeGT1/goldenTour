package com.goldentour.jee.controllers;

import java.util.ArrayList;
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
import com.goldentour.jee.viewBeans.AccomodationViewBeen;
import com.goldentour.jee.viewBeans.BookingViewBean;
import com.goldentour.jee.viewBeans.DestinationViewBean;

@RestController
@RequestMapping("/booking")
public class BookingController {


	@Autowired
	private BookingService bookingService;
	@Autowired
	private DestinationService destinationServices;
	@Autowired
	private AccomodationService accomodationService;


	//--------------Ritorna tutte le prenotazioni------------------------------------------------------
	@RequestMapping(value = "/to/showAllBooking", method = RequestMethod.GET)
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

	//--------------Ritorna tutte le prenotazioni del tour operator selezionato-----------------------
		@RequestMapping(value = "/to/showTOBooking", method = RequestMethod.GET)
		public ResponseEntity<List<Booking>> ListAllBooking(@PathVariable("idTourOperator") int idTourOperator) {
			List<Booking> booking;
		try {		
			booking = bookingService.findAllBooking(idTourOperator);
			if (booking.isEmpty()) {
				//stampare messaggio in front "tour operator non ha prenotazioni"
				return new ResponseEntity<List<Booking>>(booking, HttpStatus.OK);
			}
			return new ResponseEntity<List<Booking>>(booking, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//--------------Ritorna tutte le destinazioni disponibili----------------------------------------- OK
	@RequestMapping(value = "/to/showAllDestination", method = RequestMethod.GET)
	public ResponseEntity<List<DestinationViewBean>> AllAvaibleDestination() {
		List<DestinationViewBean> destinationsList;
		try {
			destinationsList = destinationServices.FindAllDestination();
			if (destinationsList.isEmpty()) {
				return new ResponseEntity<List<DestinationViewBean>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<DestinationViewBean>>(destinationsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DestinationViewBean>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}

	//---------------Ritorna tutti gli alberghi di una destinazione------------------------------------- OK
	@RequestMapping(value = "/to/showAccomodatio/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AccomodationViewBeen>> SearchAccomodationByDestination(@PathVariable("id") long id) {

		List<AccomodationViewBeen> accomodationsList= new ArrayList<AccomodationViewBeen>();

		try {
			accomodationsList = (List<AccomodationViewBeen>) accomodationService.FindAccomodationByDestination(id);
			if (accomodationsList.isEmpty()) {
				return new ResponseEntity<List<AccomodationViewBeen>>(HttpStatus.NO_CONTENT);

			}
				return new ResponseEntity<List<AccomodationViewBeen>>(accomodationsList, HttpStatus.OK);
				
			} catch (Exception  e) {
			e.printStackTrace();
			return new ResponseEntity<List<AccomodationViewBeen>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	//--------------Modifica prenotazione di un utente--------------------------------------------------- OK

		@RequestMapping(value = "/to/update/{idUser}", method = RequestMethod.PUT)
		public ResponseEntity<BookingViewBean> updateBooking(@PathVariable("idUser") int idUser, @RequestBody BookingViewBean booking) {
			try {
				booking.setId(idUser);
				bookingService.update(booking);
				return new ResponseEntity<BookingViewBean>(booking, HttpStatus.OK);
				
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<BookingViewBean>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


	//------------Creazione della prenotazione---------------------------------------------------------
	@RequestMapping(value = "/to/newBooking", method = RequestMethod.POST)
	public ResponseEntity<Void> createBooking(@RequestBody BookingViewBean booking, UriComponentsBuilder ucBuilder) {
		//bookingService.saveBooking(booking);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/booking/id").buildAndExpand(booking.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}
	
	//------------Ritorna la prenotazione dato un id--------------------------------------------------- OK
	@RequestMapping(value = "/to/showBooking/{id}", method = RequestMethod.GET)
	public ResponseEntity<BookingViewBean> showBooking(@PathVariable("id") long id){
		BookingViewBean booking = bookingService.find(id);
		return new ResponseEntity<BookingViewBean>(booking, HttpStatus.OK);

	}


}
