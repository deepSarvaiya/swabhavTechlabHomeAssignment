package com.tss.service;

import com.tss.dao.UserDao;
import com.tss.model.User;

public class UserService {
    
    private UserDao userDao = new UserDao();;

//    public boolean registerUser(User user) {
//        return userDao.registerUser(user);
//        
//    }

    public User authenticate(String username, String password) {
        return userDao.login(username, password);
    }
}