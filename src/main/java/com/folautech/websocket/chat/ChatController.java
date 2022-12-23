package com.folautech.websocket.chat;


import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.folautech.websocket.message.Message;

@Controller
public class ChatController {

    
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception {
        message.setMessage(message.getMessage()+"--"+LocalDateTime.now().toString());
      return message;
    }
}
