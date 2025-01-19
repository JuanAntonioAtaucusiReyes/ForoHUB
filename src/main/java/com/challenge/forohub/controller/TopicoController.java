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

import com.challenge.foroHub.persistence.dto.topico.request.TopicRequest;
import com.challenge.foroHub.persistence.dto.topico.response.TopicResponse;
import com.challenge.foroHub.persistence.entity.User;
import com.challenge.foroHub.service.ITopicService;
import com.challenge.foroHub.utils.DeleteResponse;

@RestController
@RequestMapping("/topic")
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

  private final ITopicService topicService;

  @Autowired
  public TopicoController(ITopicService topicService) {
    this.topicService = topicService;
  }

  @PostMapping
  public ResponseEntity<TopicResponse> createTopic(
      @RequestBody @Valid TopicRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((User) userDetails).getId();
    TopicResponse topicResponse = topicService.createTopic(userId, request);
    return ResponseEntity.status(HttpStatus.CREATED).body(topicResponse);
  }

  @GetMapping("/all")
  public ResponseEntity<List<TopicResponse>> allTopics() {
    return ResponseEntity.ok(topicService.getAllTopics());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicResponse> getTopic(@PathVariable Long id) {
    return ResponseEntity.ok(topicService.getTopicById(id));
  }

  @GetMapping
  public ResponseEntity<List<TopicResponse>> getAlltopicByUser(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((User) userDetails).getId();
    return ResponseEntity.ok(topicService.getAllTopicsByUser(userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TopicResponse> updateTopic(
      @PathVariable Long id,
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody @Valid TopicRequest request) {
    Long userId = ((User) userDetails).getId();
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(topicService.updateTopic(userId, id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteTopic(
      @PathVariable Long id,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((User) userDetails).getId();
    return ResponseEntity.ok(topicService.deleteTopic(id, userId));
  }
}
