package com.challenge.foroHub.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.foroHub.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameIgnoreCase(String username);
}
