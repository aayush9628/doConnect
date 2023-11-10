package com.example.doConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doConnect.entity.User;
import com.example.doConnect.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/getuserbyid/{id}")
	public User getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}
	
	@GetMapping("/getbyname/{username}")
	public User getByName(@PathVariable String username) {
		return service.getByName(username);
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public void deleteById(@PathVariable int id) {
		service.deleteById(id);
	}
}
