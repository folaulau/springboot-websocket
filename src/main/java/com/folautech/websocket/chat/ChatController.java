package com.folautech.websocket.chat;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.folautech.websocket.dto.MessageDTO;
import com.folautech.websocket.message.Message;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(value = "*")
@Slf4j
@Controller
public class ChatController {
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    
//    @MessageMapping("/chat/info")
//    @SendTo("/topic/messages")
//    public Message chat(@Payload MessageDTO message) throws Exception {
//        log.info("chat");
//      return Message.builder()
//              .id(1L)
//              .message(message.getMessage()+"--"+LocalDateTime.now().toString())
//              .build();
//    }
    
//    @CrossOrigin(origins = "http://localhost:5500")
    @MessageMapping("/chat/info")
//    @SendTo("/topic/messages")
    public void chat(@Payload MessageDTO message) throws Exception {
        log.info("chat");
        Message msg = Message.builder()
              .id(1L)
              .message(message.getMessage()+"--"+LocalDateTime.now().toString())
              .build();
        
        simpMessagingTemplate.convertAndSend("/topic/messages", msg);
    }
}
