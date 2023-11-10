package com.example.doConnect.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.doConnect.entity.Role;
import com.example.doConnect.entity.Token;
import com.example.doConnect.entity.TokenType;
import com.example.doConnect.entity.User;
import com.example.doConnect.repository.TokenRepository;
import com.example.doConnect.repository.UserRepository;
import com.example.doConnect.request.RegisterRequest;
import com.example.doConnect.request.UserRequest;
import com.example.doConnect.response.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final TokenRepository tokenRepository;

	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setRole(request.getRole());
		userRepo.save(user);
		var jwtToken = jwtService.generateToken(user);
		saveUserToken(user, jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(UserRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		var user = userRepo.findUserByUsername(request.getUsername());

		var jwtToken = jwtService.generateToken(user);
		saveUserToken(user, jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder()
				.user(user)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();
		tokenRepository.save(token);
	}
	
	private void revokeUserToken(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if(validUserTokens.isEmpty()) {
			return ;
		}
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

}
