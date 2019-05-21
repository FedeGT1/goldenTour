package com.goldentour.jee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Destination;
import com.goldentour.jee.entities.Transport;
import com.goldentour.jee.services.AccomodationService;
import com.goldentour.jee.services.BookingService;
import com.goldentour.jee.services.DestinationService;
import com.goldentour.jee.services.TransportService;
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
	private TransportService transportServices;
	@Autowired
	private AccomodationService accomodationServices;

	@RequestMapping(value = "/create/", method = RequestMethod.GET)
	public ResponseEntity<List<Destination>> CreateBooking() {
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

	@RequestMapping(value = "/create/Accomodation/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<Accomodation>> SearchAccomodationByDestination(@PathVariable("id") int id) {

		List<Accomodation> accomodations;

		try {
			accomodations = accomodationServices.FindAccomodationByDestination(id);
			if (accomodations.isEmpty()) {
				return new ResponseEntity<List<Accomodation>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Accomodation>>(accomodations,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Accomodation>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}//end SearchAccomodationByDestination
	
	@RequestMapping(value = "/create/Transport/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<Transport>> SearchTransportByDestination(@PathVariable("id") int id) {

		List<Transport> transports;

		try {
			transports = transportServices.FindTransportByDestination(id);
			if (transports.isEmpty()) {
				return new ResponseEntity<List<Transport>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Transport>>(transports,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Transport>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}//end SearchTransportByDestination

}
