package com.challenge.foroHub.persistence.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.challenge.foroHub.persistence.entity.Role;

public record UpdateUserRequest(
    @NotBlank(message = "el name no puede ir vacio")
    String name,
    @DefaultValue(value = "USER")
    Role role,
    @NotBlank(message = "el name no puede ir vacio")
    String password
) {

}
