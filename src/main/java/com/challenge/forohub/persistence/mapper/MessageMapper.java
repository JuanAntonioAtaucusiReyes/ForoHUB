package com.challenge.foroHub.persistence.mapper;

import java.util.List;

import com.challenge.foroHub.persistence.dto.message.request.MessageRequest;
import com.challenge.foroHub.persistence.dto.message.response.MessageResponse;
import com.challenge.foroHub.persistence.entity.Message;

public class MessageMapper {

  public static MessageResponse toMessageDto(Message message) {
    if (message == null) {
      return null;
    }

    return new MessageResponse(
        message.getId(),
        message.getContent()
    );
  }

  public static List<MessageResponse> toMessageDtoList(List<Message> messages) {
    if (messages == null) {
      return null;
    }

    return messages.stream()
        .map(MessageMapper::toMessageDto)
        .toList();
  }

  public static Message toMessageEntity(MessageRequest messageDto) {
    if (messageDto == null) {
      return null;
    }

    Message message = new Message();
    message.setContent(messageDto.content());

    return message;
  }

  public static void updateMessageEntity(Message oldMessage, MessageRequest messageDto) {
    if (oldMessage != null && messageDto != null) {
      oldMessage.setContent(messageDto.content());
    }
  }
}
