package com.challenge.foroHub.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.foroHub.persistence.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByTopicId(Long topicId);

  List<Message> findAllByUserId(Long userId);
}
