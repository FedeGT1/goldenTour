package com.goldentour.jee.controllers;


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

import com.goldentour.jee.entities.User;
import com.goldentour.jee.services.UserService;
import com.goldentour.jee.viewBeans.UserViewBean;

@RestController
@RequestMapping("/security")
public class UserController {
	@Autowired
	private UserService userService;




	//--------------Visualizza Anagrafica utente----------------------------------------------------------
	@RequestMapping(value = "/user/{idUser}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/user/{idUser}/update", method = RequestMethod.PUT)
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserViewBean> authorize(@RequestBody UserViewBean userViewBean) {
		try {
			userViewBean = userService.authorize(userViewBean.getUsername(), userViewBean.getPassword());
			if (userViewBean != null) return new ResponseEntity<UserViewBean>(userViewBean, HttpStatus.ACCEPTED);
			else return new ResponseEntity<UserViewBean>(userViewBean, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserViewBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ResponseEntity<User> saveUser() {
		//TODO
		return null;

	}
	
	// Ricerca di un utente se � gi� nel database
	@RequestMapping(value = "/to/user/{FiscalCode}/", method = RequestMethod.POST)
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
	
	// Creazione un nuovo utente
	@RequestMapping(value = "/to/newUser/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/id").buildAndExpand(user.getIduser()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

}




