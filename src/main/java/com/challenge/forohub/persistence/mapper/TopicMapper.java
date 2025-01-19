package com.challenge.foroHub.persistence.mapper;

import java.util.List;

import com.challenge.foroHub.persistence.dto.topico.request.TopicRequest;
import com.challenge.foroHub.persistence.dto.topico.response.TopicResponse;
import com.challenge.foroHub.persistence.entity.Topic;
import com.challenge.foroHub.persistence.entity.TopicStatus;

public class TopicMapper {

  public static TopicResponse toTopicDto(Topic topic) {
    if (topic == null) {
      return null;
    }
    return new TopicResponse(
        topic.getId(),
        topic.getTitle(),
        topic.getCurso(),
        topic.getCreatedAt(),
        UserMapper.toUserDto(topic.getUser()),
        MessageMapper.toMessageDtoList(topic.getMessages())
    );
  }

  public static List<TopicResponse> toTopicDtoList(List<Topic> topics) {
    if (topics == null) {
      return null;
    }

    return topics.stream()
        .map(TopicMapper::toTopicDto)
        .toList();
  }

  public static Topic toTopicEntity(TopicRequest topicDto) {
    if (topicDto == null) {
      return null;
    }

    Topic topic = new Topic();
    topic.setTitle(topicDto.title());
    topic.setCurso(topicDto.curso());
    topic.setStatus(TopicStatus.PUBLIC);

    return topic;
  }

  public static void updateTopicEntity(Topic oldTopic, TopicRequest topicDto) {
    if (oldTopic != null && topicDto != null) {
      if (topicDto.title() != null) {
        oldTopic.setTitle(topicDto.title());
      }
      if (topicDto.curso() != null) {
        oldTopic.setCurso(topicDto.curso());
      }
    }
  }
}
