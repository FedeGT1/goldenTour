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
import com.goldentour.jee.viewBeans.UserViewBean;

@RestController
@RequestMapping("/booking")
public class BookingController {


	@Autowired
	private BookingService bookingService;
	@Autowired
	private DestinationService destinationServices;
	@Autowired
	private AccomodationService accomodationService;


	//--------------Ritorna tutte le prenotazioni di un utente o del tour operator-------------------- OK
	@RequestMapping(value = "/showAllBooking/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<BookingViewBean>> getAllBooking(@PathVariable("id") long idUser) {
		List<BookingViewBean> booking;

		try {		
			booking = bookingService.findAllBooking(idUser);
			if (booking.isEmpty()) {
				return new ResponseEntity<List<BookingViewBean>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<BookingViewBean>>(booking, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<BookingViewBean>>(HttpStatus.INTERNAL_SERVER_ERROR);
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


	//------------Creazione della prenotazione--------------------------------------------------------- OK
	@RequestMapping(value = "/to/newBooking", method = RequestMethod.POST)
	public ResponseEntity<BookingViewBean> createBooking(@RequestBody BookingViewBean currentBooking) {
		try{
        	Booking booking = bookingService.createNewBooking(currentBooking);
            if (booking != null)
            	return new ResponseEntity<BookingViewBean>(currentBooking, HttpStatus.CREATED);
        } catch (Exception e) { 
        		return new ResponseEntity<BookingViewBean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		return null;
	}
	
	//------------Ritorna la prenotazione dato un id--------------------------------------------------- OK
	@RequestMapping(value = "/to/showBooking/{id}", method = RequestMethod.GET)
	public ResponseEntity<BookingViewBean> showBooking(@PathVariable("id") long id){
		BookingViewBean booking = bookingService.find(id);
		return new ResponseEntity<BookingViewBean>(booking, HttpStatus.OK);

	}


}
