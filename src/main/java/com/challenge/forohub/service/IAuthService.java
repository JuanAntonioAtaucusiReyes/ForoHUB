package com.challenge.foroHub.service;

import com.challenge.foroHub.persistence.dto.auth.request.LoginRequest;
import com.challenge.foroHub.persistence.dto.auth.request.RegisterRequest;
import com.challenge.foroHub.persistence.dto.auth.response.AuthResponse;
import com.challenge.foroHub.persistence.dto.auth.response.RegisterResponse;

public interface IAuthService {

  AuthResponse login(LoginRequest request);

  RegisterResponse register(RegisterRequest request);
}
