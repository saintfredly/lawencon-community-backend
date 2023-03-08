package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.User;

@Service
public class UserService implements UserDetailsService {
	private final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> user = userDao.getByEmail(username);

		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(username, user.get().getPasswords(),
					new ArrayList<>());
		}
		throw new UsernameNotFoundException("Email and Password Not Match!");
	}

	public Optional<User> getByEmail(String email) {
		return userDao.getByEmail(email);
	}
}
