package com.jyo.practiceprojectformList.service;

import com.jyo.practiceprojectformList.Repository.MessageRepository;
import com.jyo.practiceprojectformList.entity.Message;
import com.jyo.practiceprojectformList.entity.MessageType;
//import com.jyo.practiceprojectformList.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getPublicMessages() {
        return messageRepository.findByMessageType(MessageType.PUBLIC);
    }

    public List<Message> getPrivateMessages(String user1, String user2) {
        return messageRepository.findBySenderEmailAndReceiverEmailOrReceiverEmailAndSenderEmail(
                user1, user2, user1, user2
        );
    }


    public Message savePrivateMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());       // Set the current timestamp
        message.setMessageType(MessageType.PRIVATE);            // Ensure it's marked as PRIVATE
        return messageRepository.save(message);          // Save to DB
    }

}
