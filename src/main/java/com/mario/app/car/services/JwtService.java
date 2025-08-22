package com.mario.app.car.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.expiration-time}")
  private long expirationTime;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Long extractUserId(String token) {
    return extractClaim(token, claims -> claims.get("userId", Long.class));
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(String username) {
    return generateToken(new HashMap<>(), username);
  }

  public String generateToken(String username, Long userId) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", userId);
    claims.put("username", username);
    return generateToken(claims, username);
  }

  public String generateToken(Map<String, Object> extraClaims, String username) {
    return Jwts
        .builder()
        .claims(extraClaims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(getSignInKey())
        .compact();
  }

  public boolean isTokenValid(String token, String username) {
    final String tokenUsername = extractUsername(token);
    return (username.equals(tokenUsername)) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = java.util.Base64.getDecoder().decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
