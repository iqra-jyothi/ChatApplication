package com.jyo.practiceprojectformList.DTO;


import com.jyo.practiceprojectformList.entity.MessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private String senderEmail;
    private String senderName;
    private String content;
    private MessageType messageType;
    private String receiverEmail;
    private LocalDateTime timestamp;// null for public messages
}
