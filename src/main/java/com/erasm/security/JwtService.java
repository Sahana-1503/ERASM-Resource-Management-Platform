package com.erasm.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	private static final String SECRET_KEY="mysecretkeymysecretkeymysecretkey12345";
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	public String extractUsername(String token) {
		Claims claims=Jwts.parser()
				.verifyWith((javax.crypto.SecretKey) getSignKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.getSubject();
	}
	public boolean isTokenValid(String token,String username) {
		String extractedUsername=extractUsername(token);
		return extractedUsername.equals(username);
	}
}
