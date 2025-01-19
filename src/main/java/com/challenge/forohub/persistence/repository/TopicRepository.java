package com.challenge.foroHub.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.foroHub.persistence.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  List<Topic> findAllByUserId(Long userId);
}
