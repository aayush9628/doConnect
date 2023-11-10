package com.example.doConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.doConnect.entity.User;
import com.example.doConnect.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}

	public User getUserById(int id) {
		return userRepo.findById(id).orElse(null);
	}

	public User getByName(String username) {
		return userRepo.findUserByUsername(username);
	}

	public void deleteById(int id) {
		userRepo.deleteById(id);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
	
}
