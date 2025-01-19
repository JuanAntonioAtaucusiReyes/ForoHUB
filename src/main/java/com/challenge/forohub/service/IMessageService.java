package com.challenge.foroHub.service;

import java.util.List;

import com.challenge.foroHub.persistence.dto.message.request.MessageRequest;
import com.challenge.foroHub.persistence.dto.message.request.UpdateMessageRequest;
import com.challenge.foroHub.persistence.dto.message.response.MessageResponse;
import com.challenge.foroHub.utils.DeleteResponse;

public interface IMessageService {

  List<MessageResponse> allMessages();

  List<MessageResponse> allMessagesByUser(Long userId);

  List<MessageResponse> allMessagesByTopic(Long topicId);

  MessageResponse getMessageById(Long messageId);

  MessageResponse createMessage(MessageRequest request, Long userId);

  MessageResponse updateMessage(UpdateMessageRequest request, Long messageId, Long userId);

  DeleteResponse deleteMessage(Long userId, Long messageId);
}
