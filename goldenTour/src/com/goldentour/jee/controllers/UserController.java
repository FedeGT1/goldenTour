package com.goldentour.jee.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goldentour.jee.entities.User;
import com.goldentour.jee.services.UserService;
import com.goldentour.jee.viewBeans.UserViewBean;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching user with id:" + id);
        User user;
        try {
            user = userService.findByID();
            if (user == null) {
                System.out.println("User with id:" + id + "not found");
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<User>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
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

}




    