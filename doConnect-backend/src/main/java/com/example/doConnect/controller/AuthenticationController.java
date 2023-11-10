package com.example.doConnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doConnect.request.RegisterRequest;
import com.example.doConnect.request.UserRequest;
import com.example.doConnect.response.AuthenticationResponse;
import com.example.doConnect.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationService service;
	
	@PostMapping("/addnewuser")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(service.register(request));
	}
	
	@PostMapping("/loginverify")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
}
