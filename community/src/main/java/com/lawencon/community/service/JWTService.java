package com.lawencon.community.service;

import java.security.KeyPair;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lawencon.community.service.JWTService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService  {
	private final KeyPair key = Keys.keyPairFor(SignatureAlgorithm.RS256);


	public String generateJWT(Map<String, Object> claims) {
		final String jwt = Jwts.builder().setClaims(claims).signWith(key.getPrivate()).compact();
		return jwt;
	}

	public Map<String, Object> parseJWT(String jwt) {
		return Jwts.parserBuilder().setSigningKey(key.getPublic()).build().parseClaimsJws(jwt).getBody();
	}

}
