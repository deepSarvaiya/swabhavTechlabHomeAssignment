package com.tss.service;

import com.tss.dao.UserDao;
import com.tss.model.User;

public class UserService {
	private UserDao dao = new UserDao();

	public boolean registerUser(User user) {
		return dao.register(user);
	}
	
	public boolean validateUser(String username, String password) {
		return dao.validate(username, password);
	}
	
    public int validateUserAndGetId(String username, String password) {
        return dao.getUserIdByCredentials(username, password);
    }
}
