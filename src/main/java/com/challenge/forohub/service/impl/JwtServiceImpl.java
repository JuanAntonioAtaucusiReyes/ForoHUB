package com.challenge.foroHub.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.foroHub.persistence.entity.User;
import com.challenge.foroHub.service.IJwtService;

@Service
public class JwtServiceImpl implements IJwtService {

  @Value("${api.security.token.secret}")
  private String secretKey;
  @Value("${api.security.token.expiration}")
  private Long jwtExpiration;

  @Override
  public String extractUsername(String token) {
    return getClaimsFromToken(token).getSubject();
  }

  private Claims getClaimsFromToken(String token) {
    return Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public String generateToken(User user) {
    return Jwts.builder()
        .claims(Map.of("id", user.getId()))
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(getSignInKey())
        .compact();
  }

  @Override
  public Boolean isTokenValid(String token, User user) {
    final String username = extractUsername(token);
    return (username.equals(user.getUsername())) && !isTokenExpired(token);
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getExpiration();
  }

  private SecretKey getSignInKey() {
    final byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
