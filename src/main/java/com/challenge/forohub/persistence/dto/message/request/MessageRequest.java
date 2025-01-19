package com.challenge.foroHub.persistence.dto.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MessageRequest(
    @NotBlank
    @Size(min = 4, max = 60, message = "Minimo de 4 y maximo de 60")
    String content,
    @JsonProperty("topic_id")
    Long topicId
) {

}
