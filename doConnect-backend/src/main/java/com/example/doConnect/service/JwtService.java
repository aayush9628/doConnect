package com.example.doConnect.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.doConnect.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "667952ac95d82283beaa9e8af0dc2c369276f46259c1164700ac304175b26a6f";
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(Map<String ,Object> extraClaims, User userDetails) {
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateToken(User userDetails) {
		HashMap<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("role", userDetails.getRole());
		return generateToken(extraClaims, userDetails);
	}
	
	public String generateRefreshToken(User userDetails) {
		return generateToken(userDetails);
	}
	
	public boolean isTokenValid(String token, User userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
}
