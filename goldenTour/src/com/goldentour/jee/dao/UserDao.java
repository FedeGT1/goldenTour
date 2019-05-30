package com.goldentour.jee.dao;

import com.goldentour.jee.entities.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    User findByUsernameAndPassword(String username, String password);

    List<User> findByNameAndLastname(String name, String lastname);

}
