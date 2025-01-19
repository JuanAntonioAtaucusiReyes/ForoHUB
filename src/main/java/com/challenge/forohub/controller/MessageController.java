package com.challenge.foroHub.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.foroHub.persistence.dto.message.request.MessageRequest;
import com.challenge.foroHub.persistence.dto.message.request.UpdateMessageRequest;
import com.challenge.foroHub.persistence.dto.message.response.MessageResponse;
import com.challenge.foroHub.persistence.entity.User;
import com.challenge.foroHub.service.IMessageService;
import com.challenge.foroHub.utils.DeleteResponse;

@RestController
@RequestMapping("/message")
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "bearer-key")
public class MessageController {

  private final IMessageService messageService;

  @Autowired
  public MessageController(IMessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<MessageResponse>> findAll() {
    return ResponseEntity.ok(messageService.allMessages());
  }

  @GetMapping("/me")
  public ResponseEntity<List<MessageResponse>> findByUser(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((User) userDetails).getId();
    return ResponseEntity.ok(messageService.allMessagesByUser(userId));
  }

  @GetMapping("/{topicId}")
  public ResponseEntity<List<MessageResponse>> findByTopic(@PathVariable Long topicId) {
    return ResponseEntity.ok(messageService.allMessagesByTopic(topicId));
  }

  @PostMapping
  public ResponseEntity<MessageResponse> create(
      @RequestBody @Valid MessageRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((User) userDetails).getId();
    MessageResponse response = messageService.createMessage(request, userId);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{messageId}")
  public ResponseEntity<MessageResponse> update(
      @PathVariable Long messageId,
      @RequestBody @Valid UpdateMessageRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((User) userDetails).getId();
    MessageResponse response = messageService.updateMessage(request, messageId, userId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @DeleteMapping("/{messageId}")
  public ResponseEntity<DeleteResponse> delete(
      @PathVariable Long messageId,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((User) userDetails).getId();
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(messageService.deleteMessage(userId, messageId));
  }
}
