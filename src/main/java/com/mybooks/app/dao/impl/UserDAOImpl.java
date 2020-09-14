package com.mybooks.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.mybooks.app.dao.UserDAO;
import com.mybooks.app.entities.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public User save(User user) {
		return user;
	}
}
