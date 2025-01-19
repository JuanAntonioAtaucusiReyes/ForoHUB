package com.challenge.foroHub.persistence.dto.message.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateMessageRequest(
    @NotBlank
    @Size(min = 4, max = 60, message = "Minimo de 4 y maximo de 60")
    String content
) {

}
