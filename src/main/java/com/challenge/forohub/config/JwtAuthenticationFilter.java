package com.challenge.foroHub.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.challenge.foroHub.exceptions.InvalidAuthException;
import com.challenge.foroHub.persistence.repository.UserRepository;
import com.challenge.foroHub.service.IJwtService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final IJwtService jwtService;
  private final UserRepository userRepository;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(IJwtService jwtService, UserRepository userRepository,
      UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);
      String username = jwtService.extractUsername(token);
      userRepository.findByUsernameIgnoreCase(username).ifPresentOrElse(user -> {
        if (jwtService.isTokenValid(token, user)) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
              userDetails,
              null,
              userDetails.getAuthorities()
          );
          authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authtoken);
        } else {
          throw new InvalidAuthException("Token invalid");
        }
      }, () -> {
        throw new InvalidAuthException("User not found");
      });
    }

    filterChain.doFilter(request, response);
  }
}
