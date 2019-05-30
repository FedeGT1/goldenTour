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

import com.goldentour.jee.entities.User;
import com.goldentour.jee.exception.AuthenticationException;
import com.goldentour.jee.services.UserService;
import com.goldentour.jee.utils.UserUtils;
import com.goldentour.jee.viewBeans.UserViewBean;

@RestController
@RequestMapping("/security")
public class UserController {
	@Autowired
	private UserService userService;




	//--------------Visualizza Anagrafica utente---------------------------------------------------------- OK
	@RequestMapping(value = "/user/{idUser}", method = RequestMethod.GET)
	public ResponseEntity<UserViewBean> getUser(@PathVariable("idUser") int idUser) {
		UserViewBean user;
		try {
			user = userService.find(idUser);
			if (user == null) {
				return new ResponseEntity<UserViewBean>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UserViewBean>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserViewBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//--------------Modifica Anagrafica utente---------------------------------------------------------- OK
	@RequestMapping(value = "/user/update/{idUser}", method = RequestMethod.PUT)
	public ResponseEntity<UserViewBean> updateUser(@PathVariable("idUser") int idUser, @RequestBody UserViewBean user){
		try {
			user.setId(idUser);
			userService.update(user);
			return new ResponseEntity<UserViewBean>(user, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserViewBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//--------------Login utente------------------------------------------------------------------------- OK
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserViewBean> authorize(@RequestBody UserViewBean userViewBean) {
		try {
			userViewBean = userService.authorize(userViewBean.getUsername(), userViewBean.getPassword());
			if (userViewBean != null) return new ResponseEntity<>(userViewBean, HttpStatus.OK);
			

		} 
		catch(AuthenticationException b) {
		 return new ResponseEntity<>(userViewBean, HttpStatus.NOT_FOUND);
			
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	
	//--------------Ricerca utente per nome e cognome-------------------------------------------------- OK
	@RequestMapping(value = "/to/user/{name}/{lastname}", method = RequestMethod.GET)
	    public ResponseEntity<List<UserViewBean>> SearchUser(@PathVariable("name") String name, @PathVariable("lastname") String lastname) {
	        List<UserViewBean> userViewBean;
	        try {
	            userViewBean = userService.returnClients(name, lastname);
	            if (userViewBean != null)
	                return new ResponseEntity<>(userViewBean, HttpStatus.OK);
	        } catch (AuthenticationException b) {
	            userViewBean=null;
	            return new ResponseEntity<>(userViewBean, HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return null;
	    }
	
	//--------------Creazione  di un nuovo utente------------------------------------------------------ OK
    @RequestMapping(value = "/to/newUser", method = RequestMethod.POST)
    public ResponseEntity<UserViewBean> createUser(@RequestBody UserViewBean userViewBean) {
        try{
        	User user = userService.register(userViewBean);
            if (user != null)
            	return new ResponseEntity<UserViewBean>(userViewBean, HttpStatus.CREATED);
        } catch (Exception e) { 
        		return new ResponseEntity<UserViewBean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		return null;
    }

}