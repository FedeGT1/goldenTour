package com.goldentour.jee.utils;

import com.goldentour.jee.entities.User;
import com.goldentour.jee.viewBeans.UserViewBean;

public class UserUtils {
    User user;
    UserViewBean userVB;

    public static User toEntity(UserViewBean userVB){
        User user=new User();
        user.setIduser(userVB.getId());
        user.setUsername(userVB.getUsername());
        user.setPassword(userVB.getPassword());
        user.setEmail(userVB.getEmail());
        user.setName(userVB.getName());
        user.setLastname(userVB.getLastname());
        user.setBirthday(userVB.getBirthday());
        user.setAddress(userVB.getAddress());
        user.setCity(userVB.getCity());
        user.setCap(userVB.getCap());
        user.setBirthplace(userVB.getBirthplace());
        return user;
    }
}