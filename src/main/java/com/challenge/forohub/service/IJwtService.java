package com.challenge.foroHub.service;

import com.challenge.foroHub.persistence.entity.User;

public interface IJwtService {

  String extractUsername(String token);

  String generateToken(User user);

  Boolean isTokenValid(String token, User user);
}
