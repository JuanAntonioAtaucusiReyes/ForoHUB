package com.challenge.foroHub.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.challenge.foroHub.exceptions.InvalidAuthException;
import com.challenge.foroHub.exceptions.MessageNotFoundException;
import com.challenge.foroHub.exceptions.TopicNotFoundException;
import com.challenge.foroHub.exceptions.UserNotFoundException;
import com.challenge.foroHub.persistence.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidAuthException.class)
  public ResponseEntity<ApiResponse> handleInvalidAuth(InvalidAuthException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
  }

  @ExceptionHandler(MessageNotFoundException.class)
  public ResponseEntity<ApiResponse> handleMessageNotFound(MessageNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), null));
  }

  @ExceptionHandler(TopicNotFoundException.class)
  public ResponseEntity<ApiResponse> handleTopicNotFound(TopicNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), null));
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiResponse> handleUserNotFound(UserNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), null));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse> handleIllegalArgument(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse> handleArgumentsInvalid(MethodArgumentNotValidException e) {
    List<ObjectError> errors = e.getAllErrors();
    List<String> details = errors.stream()
        .map(error -> {
          if (error instanceof FieldError fieldError) {
            return fieldError.getField() + " : " + fieldError.getDefaultMessage();
          }
          return error.getDefaultMessage();
        }).toList();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), details));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> handleAllExceptions(Exception e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApiResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
  }
}
