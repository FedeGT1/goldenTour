package com.goldentour.jee.controllers;

import com.goldentour.jee.entities.Booking;
import com.goldentour.jee.exception.BookingException;
import com.goldentour.jee.services.AccomodationService;
import com.goldentour.jee.services.BookingService;
import com.goldentour.jee.services.DestinationService;
import com.goldentour.jee.viewBeans.AccomodationViewBeen;
import com.goldentour.jee.viewBeans.BookingViewBean;
import com.goldentour.jee.viewBeans.DestinationViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private DestinationService destinationServices;
    @Autowired
    private AccomodationService accomodationService;


    //--------------Ritorna tutte le prenotazioni di un utente---------------------------------------------- OK
    @RequestMapping(value = "/to/showAllBooking/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<BookingViewBean>> getAllBooking(@PathVariable("id") int idUser) {
        List<BookingViewBean> booking;

        try {
            booking = bookingService.findAllBooking(idUser);
            if (booking.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //--------------Ritorna tutte le prenotazioni del tour operator selezionato-----------------------   OK
    @RequestMapping(value = "/to/showTOBooking/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<BookingViewBean>> listAllBooking(@PathVariable("id") int idTourOperator) {
        List<BookingViewBean> booking;
        try {
            booking = bookingService.findAllBookingForTourOperator(idTourOperator);
            if (booking.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //--------------Ritorna tutte le destinazioni disponibili----------------------------------------- OK
    @RequestMapping(value = "/to/showAllDestination", method = RequestMethod.GET)
    public ResponseEntity<List<DestinationViewBean>> allAvaibleDestination() {
        List<DestinationViewBean> destinationsList;
        try {
            destinationsList = destinationServices.findAllDestination();
            if (destinationsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(destinationsList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //---------------Ritorna tutti gli alberghi di una destinazione------------------------------------- OK
    @RequestMapping(value = "/to/showAccomodatio/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AccomodationViewBeen>> searchAccomodationByDestination(@PathVariable("id") long id) {

        List<AccomodationViewBeen> accomodationsList;

        try {
            accomodationsList = accomodationService.findAccomodationByDestination(id);
            if (accomodationsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>(accomodationsList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //--------------Modifica prenotazione di un utente--------------------------------------------------- OK

    @RequestMapping(value = "/to/update/{idUser}", method = RequestMethod.PUT)
    public ResponseEntity<BookingViewBean> updateBooking(@PathVariable("idUser") int idUser, @RequestBody BookingViewBean booking) {
        try {
            booking.setId(idUser);
            bookingService.update(booking);
            return new ResponseEntity<>(booking, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //------------Creazione della prenotazione--------------------------------------------------------- OK
    @RequestMapping(value = "/to/newBooking", method = RequestMethod.POST)
    public ResponseEntity<BookingViewBean> createBooking(@RequestBody BookingViewBean currentBooking) {
        try {
            Booking booking = bookingService.createNewBooking(currentBooking);
            if (booking != null)
                return new ResponseEntity<>(currentBooking, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    //------------Ritorna la prenotazione dato un id--------------------------------------------------- OK
    @RequestMapping(value = "/to/showBooking/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookingViewBean> showBooking(@PathVariable("id") long id) throws BookingException {
        BookingViewBean booking = bookingService.find(id);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            throw new BookingException("Booking not found");
        }
    }


}
