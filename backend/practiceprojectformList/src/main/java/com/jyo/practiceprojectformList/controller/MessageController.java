//package com.jyo.practiceprojectformList.config;//package com.jyo.practiceprojectformList.controller;
//
////import com.jyo.practiceprojectformList.dto.MessageDTO;
//import com.jyo.practiceprojectformList.DTO.MessageDTO;
//import com.jyo.practiceprojectformList.entity.Message;
//import com.jyo.practiceprojectformList.service.MessageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.*;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//
//@RestController
//@RequiredArgsConstructor
//public class MessageController {
//
//    private final SimpMessagingTemplate messagingTemplate;
//    private final MessageService messageService;
//
//    @MessageMapping("/chat.send") // -> /app/chat.send
//    public void sendMessage(@Payload MessageDTO messageDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String senderEmail = authentication.getName(); // gets from JWT token
//
//        Message message = new Message();
//        message.setSenderEmail(senderEmail);
////        Message message = new Message();
////        message.setSenderEmail(messageDTO.getSenderEmail());
//        message.setReceiverEmail(messageDTO.getReceiverEmail());
//        message.setContent(messageDTO.getContent());
//        message.setMessageType(messageDTO.getMessageType());
//
//
//        Message saved = messageService.save(message);
//
//        if (messageDTO.getMessageType().name().equals("PUBLIC")) {
//            messagingTemplate.convertAndSend("/topic/public", saved);
//        } else {
//            messagingTemplate.convertAndSendToUser(
//                    messageDTO.getReceiverEmail(),
//                    "/queue/private",
//                    saved
//            );
//        }
//    }
//
//    @GetMapping("/message/public")
//    public List<Message> getPublicMessages() {
//        return messageService.getPublicMessages();
//    }
//
//    @GetMapping("/message/private")
//    public List<Message> getPrivateMessages(@RequestParam String user1, @RequestParam String user2) {
//        return messageService.getPrivateMessages(user1, user2);
//    }
//}



// MessageController.java (improved version)

//package com.jyo.practiceprojectformList.config;
//
//import com.jyo.practiceprojectformList.DTO.MessageDTO;
//import com.jyo.practiceprojectformList.Repository.MessageRepository;
//import com.jyo.practiceprojectformList.entity.Message;
//import com.jyo.practiceprojectformList.entity.MessageType;
////import com.jyo.practiceprojectformList.manualfilter.JwtUtils;
//import com.jyo.practiceprojectformList.manualfilter.JwtUtils;
//import com.jyo.practiceprojectformList.service.GenerateJWTtoken;
//import com.jyo.practiceprojectformList.service.MessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/message")
//public class MessageController {
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    private final MessageService messageService;
//    private final MessageRepository messageRepository;
//
//    public MessageController(MessageService messageService, MessageRepository messageRepository, SimpMessagingTemplate messagingTemplate) {
//        this.messageService = messageService;
//        this.messageRepository = messageRepository;
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @PostMapping("/private")
//    public ResponseEntity<Message> sendPrivateMessage(@RequestBody Message message,
//                                                      HttpServletRequest request) {
//        String token = extractToken(request);
//        if (token == null || !jwtUtils.validateToken(token)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        Message saved = messageService.savePrivateMessage(message);
//        return ResponseEntity.ok(saved);
//    }

//    @GetMapping("/private/{senderEmail}/{receiverEmail}")
//    public ResponseEntity<List<Message>> getPrivateMessages(
//            @PathVariable String senderEmail,
//            @PathVariable String receiverEmail,
//            HttpServletRequest request) {
//System.out.println("the sener emai"+senderEmail);
//        System.out.println("the sener emai"+receiverEmail);
//
//        String token = extractToken(request);
//        if (token == null || !jwtUtils.validateToken(token)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String authenticatedUser = jwtUtils.extractUsername(token);
//        if (!authenticatedUser.equals(senderEmail) && !authenticatedUser.equals(receiverEmail)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        List<Message> messages = messageRepository.findPrivateMessages(senderEmail, receiverEmail);
//        return ResponseEntity.ok(messages);
//    }
////
//    @GetMapping("/public")
//    public ResponseEntity<List<Message>> getAllPublicMessages() {
//        List<Message> messages = messageRepository.findByMessageType(MessageType.CHAT);
//        return ResponseEntity.ok(messages);
//    }
//
//        private final SimpMessagingTemplate messagingTemplate;
//        @MessageMapping("/chat.send")
//        @PostMapping("/public")// -> /app/chat.send
//    public void sendMessage(@Payload MessageDTO messageDTO) {
//            System.out.println("the message"+messageDTO);
//        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String senderEmail = authentication.getName(); // gets from JWT token
//
//        Message message = new Message();
//        message.setSenderEmail(senderEmail);
////        Message message = new Message();
////        message.setSenderEmail(messageDTO.getSenderEmail());
//        message.setReceiverEmail(messageDTO.getReceiverEmail());
//        message.setContent(messageDTO.getContent());
//        message.setMessageType(messageDTO.getMessageType());
//        message.setTimestamp(messageDTO.getTimestamp());
//
//        Message saved = messageService.save(message);
//
//        if (messageDTO.getMessageType().name().equals("PUBLIC")) {
//            messagingTemplate.convertAndSend("/topic/public", saved);
//        } else {
//            messagingTemplate.convertAndSendToUser(
//                    messageDTO.getReceiverEmail(),
//                    "/queue/private",
//                    saved
//            );
//        }
//    }
//
//
//    private String extractToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }
//}



package com.jyo.practiceprojectformList.controller;

import com.jyo.practiceprojectformList.DTO.MessageDTO;
import com.jyo.practiceprojectformList.Repository.MessageRepository;
import com.jyo.practiceprojectformList.entity.Message;
import com.jyo.practiceprojectformList.entity.MessageType;
import com.jyo.practiceprojectformList.component.JwtUtils;
import com.jyo.practiceprojectformList.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

        import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.List;



@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private JwtUtils jwtUtils;

    private final MessageService messageService;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(
            MessageService messageService,
            MessageRepository messageRepository,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.messageService = messageService;
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // 🔁 Fetch all public messages
    @GetMapping("/public")
    public ResponseEntity<List<Message>> getAllPublicMessages() {
        List<Message> messages = messageRepository.findByMessageType(MessageType.PUBLIC);
        return ResponseEntity.ok(messages);
    }

    // 🔁 Fetch private messages between two users
    @GetMapping("/private/{senderEmail}/{receiverEmail}")
    public ResponseEntity<List<Message>> getPrivateMessages(
            @PathVariable String senderEmail,
            @PathVariable String receiverEmail,
            HttpServletRequest request
    ) {
//        String token = extractToken(request);
//        if (token == null || !jwtUtils.validateToken(token)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String authenticatedUser = jwtUtils.extractUsername(token);
//        if (!authenticatedUser.equals(senderEmail) && !authenticatedUser.equals(receiverEmail)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }

        List<Message> messages = messageRepository.findPrivateMessages(senderEmail, receiverEmail);
        return ResponseEntity.ok(messages);
    }




    // 🔁 Handle sending public and private messages
    @MessageMapping("/chat.send") // From frontend to /app/chat.send
    public void handleWebSocketMessage(@Payload MessageDTO messageDTO, Principal principal) {
        System.out.println("Message received, but method not called..."+messageDTO);
        String senderEmail = principal.getName();// Comes from WebSocket handshake user
        System.out.println("the sender email"+senderEmail);
        String receiverEmail=messageDTO.getReceiverEmail();

        System.out.println("the receiver email id"+receiverEmail);
        Message message = new Message();
        message.setSenderName(messageDTO.getSenderName());
        message.setSenderEmail(senderEmail);
        message.setReceiverEmail(messageDTO.getReceiverEmail());
        message.setContent(messageDTO.getContent());
        message.setMessageType(messageDTO.getMessageType());
        message.setTimestamp(messageDTO.getTimestamp());

        Message saved = messageService.save(message);

        if (messageDTO.getMessageType() == MessageType.PUBLIC) {
            messagingTemplate.convertAndSend("/topic/public", saved);
        } else {
            messagingTemplate.convertAndSendToUser(
                    messageDTO.getReceiverEmail(),
                    "/queue/private",
                    saved
            );
            System.out.println("the private messed set");
        }
    }

    // 🛡️ Helper method to extract JWT token from Authorization header
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
