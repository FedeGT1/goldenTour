package com.goldentour.jee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.entities.User;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;
	
	
	//--------------Visualizza Anagrafica utente----------------------------------------------------------
	@RequestMapping(value = "/dashboard/user/{idUser}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("idUser") int idUser) {
		User user;
		try {
			user = userService.find(idUser);
			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//--------------Modifica Anagrafica utente----------------------------------------------------------
	@RequestMapping(value = "/dashboard/user/{idUser}/update", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("idUser") int idUser, @RequestBody User user){
		User currentUser;
		try {
			currentUser = userService.find(idUser);
			if (currentUser==null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			
			currentUser.setName(user.getName());
			currentUser.setLastname(user.getLastname());
			currentUser.setAddress(user.getAddress());
			currentUser.setCity(user.getCity());
			currentUser.setBirthday(user.getBirthday());
			currentUser.setBirthplace(user.getBirthplace());
			currentUser.setCap(user.getCap());

			
			userService.update(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//--------------Ritorna prenotazioni dell'utente selezionato----------------------------------------
	@RequestMapping(value = "/dashboard/user/{idUser}/booking", method = RequestMethod.GET)
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
	@RequestMapping(value = "/dashboard/to/{idUser}/booking", method = RequestMethod.GET)
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
	
}
