package com.challenge.foroHub.persistence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ApiResponse(
    String message,
    @JsonProperty("http_status")
    Integer httpStatus,
    List<String> details
) {

}
