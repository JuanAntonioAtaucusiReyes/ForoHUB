package com.challenge.foroHub.service;

import java.util.List;

import com.challenge.foroHub.persistence.dto.topico.request.TopicRequest;
import com.challenge.foroHub.persistence.dto.topico.response.TopicResponse;
import com.challenge.foroHub.utils.DeleteResponse;

public interface ITopicService {

  TopicResponse createTopic(Long userId, TopicRequest request);

  TopicResponse getTopicById(Long topicId);

  List<TopicResponse> getAllTopicsByUser(Long userId);

  List<TopicResponse> getAllTopics();

  TopicResponse updateTopic(Long userId, Long topicId, TopicRequest request);

  DeleteResponse deleteTopic(Long topicId, Long userId);
}
