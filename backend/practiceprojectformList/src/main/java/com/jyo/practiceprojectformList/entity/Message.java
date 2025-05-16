//package com.jyo.practiceprojectformList.entity;
//
////package com.yourpackage.chat.model; // <-- adjust the package name
//
//import jakarta.persistence.*;
//import lombok.*;
//import java.time.LocalDateTime;
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//public class Message {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String senderEmail;
//    private String receiverEmail; // null if public message
//    private String content;
//    @Enumerated(EnumType.STRING)
//    private MessageType messagetype ;
//    ; // CHATROOM or PRIVATE
//
//    private LocalDateTime timestamp;
////    @PrePersist
////    protected void onCreate() {
////        this.timestamp = LocalDateTime.now();
////    }
////
////    public void setMessageType(MessageType messageType) {
////        this.messagetype=messageType;
////    }
//}
//
package com.jyo.practiceprojectformList.entity;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



     // Enum for message type (e.g., PUBLIC, PRIVATE)

    private String senderEmail;
    // The email of the sender
    private String receiverEmail; // The email of the receiver (null for public messages)
    private String SenderName;
    private String content;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    private LocalDateTime timestamp;  // Time the message was sent

    // Default constructor
    public Message() {}

    // Constructor to initialize a message
    public Message(String content,String SenderName , MessageType messageType, String senderEmail, String receiverEmail, LocalDateTime timestamp ) {
        this.content = content;
        this.messageType = messageType;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.timestamp = timestamp;
        this.SenderName=SenderName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    // Optional: Override toString() for easier logging or debugging
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", messageType=" + messageType +
                ", senderEmail='" + senderEmail + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
